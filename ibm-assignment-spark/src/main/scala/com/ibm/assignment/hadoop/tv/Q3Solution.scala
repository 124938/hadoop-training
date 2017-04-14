package com.ibm.assignment.hadoop.tv

import org.apache.spark.SparkConf
import org.apache.spark.SparkContext

object Q3Solution {

  def main(args: Array[String]) = {

    // Create spark configuration
    val conf = new SparkConf()
      .setAppName("Q3_Solution")
      .setMaster("local");

    // Create spark context
    val sc = new SparkContext(conf);

    // Load channel file
    val channelProgramRdd = sc.textFile(args(0))
      .map(line => {
        line.split(",")
      })
      .filter(lineArray => {
        lineArray(1).equals("ABC")
      })
      .map(lineArray => {
        (lineArray(0), lineArray(1))
      })
      .distinct()
      
    // Load viewer file
    val programViewerRdd = sc.textFile(args(1))
      .map(line => {
        line.split(",")
      })
      .map(lineArray => {
        (lineArray(0), lineArray(1))
      })
    
    // Join, reduce, map & reduce again to get max
    val result = channelProgramRdd.join(programViewerRdd)
      .reduceByKey({
        case ((aggChannel, aggViewers), (channel, viewers)) =>
          (aggChannel, String.valueOf(aggViewers.toInt + viewers.toInt))
      })
      .map({
        case (showName, (channel, numberOfViewer)) => (channel, (showName, numberOfViewer))
      })
      .reduceByKey({
        case ((channel, numberOfViewer), (channelOther, numberOfViewerOther)) => {
          if (numberOfViewer.toInt > numberOfViewerOther.toInt) {
            (channel, numberOfViewer)
          } else {
            (channelOther, numberOfViewerOther)
          }
        }
      })
      .map({
        case (channel, (showName, numberOfViewer)) => channel + " " + showName + " " + numberOfViewer
      })
      .coalesce(1, false);
      
    // Store result into file
    result.saveAsTextFile(args(2) + "/" + System.currentTimeMillis());
    
    /*result.foreach(line =>
        println(line)
    )*/

    sc.stop();
  }
}

/*
======
Result:
======

ABC Hourly_Talking 108163

*/