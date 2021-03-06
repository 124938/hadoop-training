--1. What is the total number of viewers for shows on ABC?

SELECT c.CHANNEL_NAME, c.SHOW_NAME, SUM(v.VIEWER_NUM) 
FROM (SELECT DISTINCT CHANNEL_NAME, SHOW_NAME FROM TV_SHOW_CHANNEL) c INNER JOIN TV_SHOW_VIEWER v ON (c.SHOW_NAME = v.SHOW_NAME) 
WHERE c.CHANNEL_NAME = 'ABC' 
GROUP BY c.CHANNEL_NAME, c.SHOW_NAME;

/*

=======
Result:
=======

ABC	Almost_Games	49237
ABC	Almost_News	46592
ABC	Almost_Show	50202
ABC	Baked_Games	51604
ABC	Baked_News	47211
ABC	Cold_News	47924
ABC	Cold_Sports	52005
ABC	Dumb_Show	53824
ABC	Dumb_Talking	103894
ABC	Hot_Games	50228
ABC	Hot_Show	54378
ABC	Hourly_Cooking	54208
ABC	Hourly_Show	48283
ABC	Hourly_Talking	108163
ABC	Loud_Games	49482
ABC	Loud_Show	50820
ABC	PostModern_Games	50644
ABC	PostModern_News	50021
ABC	Surreal_News	50420
ABC	Surreal_Sports	46834

*/
