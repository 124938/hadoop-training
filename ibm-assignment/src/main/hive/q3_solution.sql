--3. What is the most viewed show on ABC channel?

SELECT c.SHOW_NAME, SUM(v.VIEWER_NUM) as VIEWER_COUNT 
FROM TV_SHOW_CHANNEL c INNER JOIN TV_SHOW_VIEWER v ON (c.SHOW_NAME = v.SHOW_NAME) 
WHERE c.CHANNEL_NAME = 'ABC' 
GROUP BY c.SHOW_NAME 
ORDER BY VIEWER_COUNT DESC LIMIT 1;

/*

=======
Result:
=======

Hourly_Talking	108163

*/

