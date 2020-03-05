USE [master];
CREATE DATABASE [admin-db]
GO
USE [admin-db]
CREATE TABLE [Posts]
(
	PostID bigint  PRIMARY KEY,
	--createdAt int NOT NULL,
	--lastRequestTime int NOT NULL
	createdAt datetime DEFAULT GETDATE() NOT NULL,
	lastCommentTime datetime DEFAULT GETDATE() NOT NULL
);
USE [master]