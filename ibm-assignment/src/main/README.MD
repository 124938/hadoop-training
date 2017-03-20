Load data in HDFS
========

#### Load data for TV Show VS Channel

```
$hadoop fs -mkdir /user/training/ibm/tv/channel

$hadoop fs -put /home/training/Downloads/intellipaat_hadoop_training/ibm/tv/channel/join2_genchanA.txt /user/training/ibm/tv/channel

$hadoop fs -put /home/training/Downloads/intellipaat_hadoop_training/ibm/tv/channel/join2_genchanB.txt /user/training/ibm/tv/channel

$hadoop fs -put /home/training/Downloads/intellipaat_hadoop_training/ibm/tv/channel/join2_genchanC.txt /user/training/ibm/tv/channel
```

#### Load data for TV Show VS Viewers

```
$hadoop fs -mkdir /user/training/ibm/tv/viewer

$hadoop fs -put /home/training/Downloads/intellipaat_hadoop_training/ibm/tv/viewer/join2_gennumA.txt /user/training/ibm/tv/viewer

$hadoop fs -put /home/training/Downloads/intellipaat_hadoop_training/ibm/tv/viewer/join2_gennumB.txt /user/training/ibm/tv/viewer

$hadoop fs -put /home/training/Downloads/intellipaat_hadoop_training/ibm/tv/viewer/join2_gennumC.txt /user/training/ibm/tv/viewer
```


