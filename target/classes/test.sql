select *from tab;

SELECT a.TABLE_NAME, c.COMMENTS, a.COLUMN_NAME, b.COMMENTS, a.DATA_TYPE, a.DATA_LENGTH, a.NULLABLE

FROM  USER_TAB_COLUMNS a, USER_COL_COMMENTS b, USER_TAB_COMMENTS c, USER_TABLES  d

where 1=1

   and  a.TABLE_NAME = D.TABLE_NAME

   and  a.TABLE_NAME=b.TABLE_NAME

   and b.TABLE_NAME=c.TABLE_NAME

   and a.COLUMN_NAME=b.COLUMN_NAME

   and a.TABLE_NAME = 'INQUIRY_BOARD'

order by a.TABLE_NAME, a.COLUMN_ID


select *from INQUIRY_BOARD

select inquiry_num,member_id,inquiry_title,inquiry_content,
to_char(inquiry_date, 'YYYY-MM-DD HH:MI:SS AM'),readnum
 from inquiry_board
 
 select inquiry_num,member_id,inquiry_title,inquiry_content,inquiry_date,readnum
 from inquiry_board
 
 SELECT to_char(inquiry_date, 'YYYY-MM-DD HH:MI:SS AM') FROM inquiry_board;
 
 commit

insert into inquiry_board(inquiry_num,member_id,inquiry_title,inquiry_content,inquiry_date,readnum) values(3,'tester','���̴�?','���ȶ�',
to_char(sysdate, 'YYYY-MM-DD HH:MI:SS AM'),0)

delete from inquiry_board
where inquiry_num = 2

 select *from electronics
