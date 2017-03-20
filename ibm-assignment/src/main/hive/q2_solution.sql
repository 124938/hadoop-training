--2. What is the number of viewers for the BAT channel?

SELECT c.CHANNEL_NAME, SUM(v.VIEWER_NUM) 
FROM TV_SHOW_CHANNEL c INNER JOIN TV_SHOW_VIEWER v ON (c.SHOW_NAME = v.SHOW_NAME) 
WHERE c.CHANNEL_NAME = 'BAT' 
GROUP BY c.CHANNEL_NAME;

/*

=======
Result:
=======

BAT	5099141

*/


