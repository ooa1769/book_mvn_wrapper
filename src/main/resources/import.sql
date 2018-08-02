insert into member(member_id, email, name, password, enabled, create_date, modified_date) values(1L, 'test1@test.com', '김남열', '$2a$10$Jxa0uY7zveD/YcBBjXNoGuMcnI98/uwuA8biuEyjVPvi2B4jGi04C', 'Y','2018-07-13 23:08:42.859','2018-07-13 23:08:42.859');
insert into member(member_id, email, name, password, enabled, create_date, modified_date) values(2L, 'test2@test.com', '김남일', '$2a$10$Jxa0uY7zveD/YcBBjXNoGuMcnI98/uwuA8biuEyjVPvi2B4jGi04C', 'Y','2018-07-13 23:08:42.859','2018-07-13 23:08:42.859');

insert into bookmark(bookmark_id, isbn, api_type, url, title, member_id, create_date, modified_date) values(1L, '9791188621279', 'KAKAO', '/bookmark/kakao?page=1&size=1&query=9791188621279&target=isbn&category=ALL&sort', '스프링 부트로 배우는 자바 웹 개발', 1L, '2018-07-13 23:08:42.859','2018-07-13 23:08:42.859');
insert into bookmark(bookmark_id, isbn, api_type, url, title, member_id, create_date, modified_date) values(2L, '9788960777330', 'KAKAO', '/bookmark/kakao?page=1&size=1&query=9788960777330&target=isbn&category=ALL&sort', '자바 ORM 표준 JPA 프로그래밍', 1L, '2018-07-13 23:08:42.859','2018-07-13 23:08:42.859');

insert into search_history(id, search_date, search_keyword, member_id) values(1L, '2018-07-13 23:08:42.859', '스프링부트', 1L);
insert into search_history(id, search_date, search_keyword, member_id) values(2L, '2018-07-13 23:08:44.859', '미움받을용기', 1L);