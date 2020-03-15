USE [master];
CREATE DATABASE [admin-db]
GO
USE [admin-db]
CREATE TABLE [posts]
(
	post_id bigint  PRIMARY KEY,
	created_at datetime DEFAULT GETDATE() NOT NULL,
	last_comment_time datetime DEFAULT GETDATE() NOT NULL,
	active_flag bit DEFAULT 1 NOT NULL
);
USE [master]