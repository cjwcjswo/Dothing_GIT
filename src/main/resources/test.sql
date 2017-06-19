select *from tab;

select *from point;
select *from member;

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

insert into inquiry_board(inquiry_num,member_id,inquiry_title,inquiry_content,inquiry_date,readnum) values(4,'tester','ㅎㅇ','hello',
to_char(sysdate, 'YYYY-MM-DD HH:MI:SS AM'),0)

insert into inquiry_board(inquiry_num,member_id,inquiry_title,inquiry_content,inquiry_date,readnum) values(SEQ_ID.NEXTVAL,'tester','ㅎ3','나가',
to_char(sysdate, 'YYYY-MM-DD HH:MI:SS AM'),0)

delete from inquiry_board
where inquiry_num = 4

 select *from electronics


 --게시글 번호 자동 증가
 CREATE SEQUENCE SEQ_ID INCREMENT BY 1 START WITH 1;
 
 CREATE SEQUENCE SEQ_REPLY_NUM INCREMENT BY 1 START WITH 1;
 
  CREATE SEQUENCE SEQ_NOTICE_NUM INCREMENT BY 1 START WITH 1;
  
  CREATE SEQUENCE SEQ_NOT_NUM INCREMENT BY 1 START WITH 1;

 select *from inquiry_reply;
 
 select inquiry_reply_num,reply_content,inquiry_num from inquiry_reply where inquiry_num = 1;
 
 insert into inquiry_reply(inquiry_reply_num, reply_content, inquiry_num)values(SEQ_REPLY_NUM.NEXTVAL,'ggg',7) 
 #{board.inquiryNum}
 
 
 
 
 
 select *from notice_board;
 
 insert into notice_board(
		notice_num,member_id,notice_title,notice_content
		,notice_date,readnum)
		values(SEQ_NOT_NUM.NEXTVAL,'tester','블랙 회원 안내입니다.','응 업써~',
		to_char(sysdate, 'YYYY-MM-DD HH:MI:SS AM'),0)
		
		
		
select count(*) from notice_board where notice_num=3
 