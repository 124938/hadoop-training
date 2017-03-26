Map-Reduce Solution
========

#### 1. What is the total number of viewers for shows on ABC?

Command:

>java com.ibm.assignment.hadoop.tv.q1.Q1Driver /home/shrey/my_work/hadoop-training/ibm-assignment/data/tv/viewer/*.txt /home/shrey/my_work/hadoop-training/ibm-assignment/data/tv/channel/*.txt  /home/shrey/my_work/hadoop-training/ibm-assignment/output/q1
		
Result :

	```
	============
	part-r-0000
	============
	
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

	
	```
	
#### 2. What is the number of viewers for the BAT channel?

Command:

>java com.ibm.assignment.hadoop.tv.q2.Q2Driver /home/shrey/my_work/hadoop-training/ibm-assignment/data/tv/viewer/*.txt /home/shrey/my_work/hadoop-training/ibm-assignment/data/tv/channel/*.txt  /home/shrey/my_work/hadoop-training/ibm-assignment/output/q2
		
Result :

	```
	============
	part-r-0000
	============
	
	BAT	3031762
	
	```	

#### 3. What is the most viewed show on ABC channel?

```
TODO
```

#### 4. What are the aired shows on ZOO,NOX, ABC channels ?


