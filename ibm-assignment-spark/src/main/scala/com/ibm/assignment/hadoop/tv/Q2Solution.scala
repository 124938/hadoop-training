package com.ibm.assignment.hadoop.tv

import org.apache.spark.SparkConf
import org.apache.spark.SparkContext

object Q2Solution {

  def main(args: Array[String]) = {

    // Create spark configuration
    val conf = new SparkConf()
      .setAppName("Q2_Solution")
      .setMaster("local");

    // Create spark context
    val sc = new SparkContext(conf);

    // Load channel file
    val channelProgramRdd = sc.textFile(args(0))
      .map(line => {
        line.split(",")
      })
      .filter(lineArray => {
        lineArray(1).equals("BAT")
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
    
    // Join, map & reduce
    val result = channelProgramRdd.join(programViewerRdd)
      .map({
        case (showName, (channel, numberOfViewer)) => (channel, numberOfViewer)
      })
      .reduceByKey((aggViewers, viewers) => String.valueOf(aggViewers.toInt + viewers.toInt), 1)
      .map({
        case (channel, numberOfViewer) => channel + " " + numberOfViewer
      })
            
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

BAT 3031762

*/