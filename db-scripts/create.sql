USE [master];
CREATE DATABASE [admin-db]
GO
USE [admin-db]
CREATE TABLE [Posts]
(
	PostID bigint  PRIMARY KEY,
	createdAt datetime DEFAULT GETDATE() NOT NULL,
	lastCommentTime datetime DEFAULT GETDATE() NOT NULL,
	activeFlag bit DEFAULT 1 NOT NULL
);
USE [master]