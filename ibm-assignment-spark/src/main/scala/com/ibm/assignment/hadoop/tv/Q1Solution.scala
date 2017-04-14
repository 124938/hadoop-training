package com.ibm.assignment.hadoop.tv

import org.apache.spark.SparkConf
import org.apache.spark.SparkContext

object Q1Solution {

  def main(args: Array[String]) = {

    // Create spark configuration
    val conf = new SparkConf()
      .setAppName("Q1_Solution")
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
    
    // Join and reduce
    val result = channelProgramRdd.join(programViewerRdd)
      .reduceByKey({
        case ((aggChannel, aggViewers), (channel, viewers)) =>
          (aggChannel, String.valueOf(aggViewers.toInt + viewers.toInt))
      })
      .sortByKey(true, 1)
      .map({
        case (showName, value) => value._1 + " " + showName + " " + value._2
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

ABC Almost_Games 49237
ABC Almost_News 46592
ABC Almost_Show 50202
ABC Baked_Games 51604
ABC Baked_News 47211
ABC Cold_News 47924
ABC Cold_Sports 52005
ABC Dumb_Show 53824
ABC Dumb_Talking 103894
ABC Hot_Games 50228
ABC Hot_Show 54378
ABC Hourly_Cooking 54208
ABC Hourly_Show 48283
ABC Hourly_Talking 108163
ABC Loud_Games 49482
ABC Loud_Show 50820
ABC PostModern_Games 50644
ABC PostModern_News 50021
ABC Surreal_News 50420
ABC Surreal_Sports 46834

*/