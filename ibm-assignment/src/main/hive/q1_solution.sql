--1. What is the total number of viewers for shows on ABC?

SELECT c.SHOW_NAME, SUM(v.VIEWER_NUM) 
FROM TV_SHOW_CHANNEL c INNER JOIN TV_SHOW_VIEWER v ON (c.SHOW_NAME = v.SHOW_NAME) 
WHERE c.CHANNEL_NAME = 'ABC' 
GROUP BY c.SHOW_NAME;

/*

=======
Result:
=======

Almost_Games	49237
Almost_News	46592
Almost_Show	50202
Baked_Games	51604
Baked_News	47211
Cold_News	47924
Cold_Sports	52005
Dumb_Show	53824
Dumb_Talking	103894
Hot_Games	50228
Hot_Show	54378
Hourly_Cooking	54208
Hourly_Show	48283
Hourly_Talking	108163
Loud_Games	49482
Loud_Show	50820
PostModern_Games	50644
PostModern_News		50021
Surreal_News	50420
Surreal_Sports	46834

*/