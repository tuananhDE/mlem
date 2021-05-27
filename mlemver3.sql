/* -- =============================================
-- Author:      Phan Khải
-- Create date: 10/05/2021
-- Description: 10đ đồ án nha ae
-- ============================================= */

/*==============================================================*/
/* Table: RoleUser                                        */
/*==============================================================*/
CREATE TABLE [dbo].[Roles](
    [role_id] [int] NOT NULL primary key,
    [role_name] [varchar](100),
	 CHECK(role_name in ('User', 'Teacher', 'Manager Categories','Manager Course','Admin'))
) 
Insert into Roles (role_id,role_name) values (1,'Admin')
Insert into Roles (role_id,role_name) values (2,'Manager Course')
Insert into Roles (role_id,role_name) values (3,'Manager Categories')
Insert into Roles (role_id,role_name) values (4,'Teacher')
Insert into Roles (role_id,role_name) values (5,'User')
/*==============================================================*/
/* Table: User                                        */
/*==============================================================*/
CREATE TABLE [dbo].[User](
    [user_id] [int] IDENTITY(100000,1) NOT NULL primary key,
    [role_id] [int],
    [email] [varchar](100),
	[full_name] [nvarchar](255),
	[gender] [nvarchar](10),
	[avatar] [varchar] (100),
	[auth_provider] [varchar](50),
	 CHECK(gender in ('Nam', 'Nữ', 'Khác')),
	 FOREIGN KEY (role_id) REFERENCES Roles(role_id)
) ON [PRIMARY]
CREATE INDEX IndexOrderItemUserID ON [User](
[user_id] DESC
)
go
/*==============================================================*/
/* Table: Categories                                        */
/*==============================================================*/
CREATE TABLE [dbo].[Categories](
    [cate_id] [int] IDENTITY NOT NULL primary key,
    [cate_name] [nvarchar](100)
)
/*==============================================================*/
/* Table: Diet                                        */
/*==============================================================*/
CREATE TABLE [dbo].[Diet](
    [diet_id] [int] IDENTITY NOT NULL primary key,
    [diet_name] [nvarchar](100)
)
/*==============================================================*/
/* Table: Post                                        */
/*==============================================================*/
CREATE TABLE [dbo].[Posts](
    [post_id] [int] IDENTITY NOT NULL primary key,
	[cate_id][int],
	[diet_id] [int],
    [user_id] [int],
	[image_post] [varchar](50),
	[date_post] [datetime],
	 FOREIGN KEY (user_id) REFERENCES [User](user_id),
	 FOREIGN KEY (cate_id) REFERENCES [Categories](cate_id),
	 FOREIGN KEY (diet_id) REFERENCES [Diet](diet_id)
) 
CREATE INDEX IndexOrderItemPostID ON [Posts](
[post_id] DESC
)
GO
/*==============================================================*/
/* Table: Ingredients                                       */
/*==============================================================*/
CREATE TABLE [dbo].[Ingredients](
    [ingredients_id] [int] IDENTITY NOT NULL primary key,
	[name_ingredients] [nvarchar](255) not null,
)
/*==============================================================*/
/* Table: Recipes                                       */
/*==============================================================*/
CREATE TABLE [dbo].[Recipes](
    [recipes_id] [int] IDENTITY NOT NULL primary key,
	[post_id] [int],
    [description] [nvarchar](MAX),
	[minutes] [int],
	FOREIGN KEY (post_id) REFERENCES [Posts](post_id)
)
/*==============================================================*/
/* Table: Ingredients                                       */
/*==============================================================*/
CREATE TABLE [dbo].[IR](
    [ir_id] [int] IDENTITY NOT NULL primary key,
	[recipes_id] [int],
    [ingredients_id] [int],
	FOREIGN KEY (recipes_id) REFERENCES [Recipes](recipes_id),
	FOREIGN KEY (ingredients_id) REFERENCES [Ingredients](ingredients_id),
)
/*==============================================================*/
/* Table: LovePost                                       */
/*==============================================================*/
CREATE TABLE [dbo].[LovePost](
    [love_post_id] [int] IDENTITY NOT NULL primary key,
	[post_id] [int],
	[user_id] [int],
    [date_love] [datetime],
	FOREIGN KEY (post_id) REFERENCES [Posts](post_id),
	FOREIGN KEY (user_id) REFERENCES [User](user_id)
)
/*==============================================================*/
/* Table: VotePost                                       */
/*==============================================================*/
CREATE TABLE [dbo].[VotePost](
    [vote_post_id] [int] IDENTITY NOT NULL primary key,
	[post_id] [int],
	[user_id] [int],
    [date_vote] [datetime],
	[number_star] [int],
	FOREIGN KEY (post_id) REFERENCES [Posts](post_id),
	FOREIGN KEY (user_id) REFERENCES [User](user_id)
)
/*==============================================================*/
/* Table: CommentPost                                        */
/*==============================================================*/
CREATE TABLE [dbo].[CommentPost](
    [comment_post_id] [int] IDENTITY NOT NULL primary key,
	[post_id] [int],
	[user_id] [int],
    [datecomment_post] [datetime],
	[textcomment_post][nvarchar](MAX),
	FOREIGN KEY (post_id) REFERENCES [Posts](post_id),
	FOREIGN KEY (user_id) REFERENCES [User](user_id)
)
/*==============================================================*/
/* Table: RepCommentPost                                        */
/*==============================================================*/
CREATE TABLE [dbo].[RepCommentPost](
    [repcomment_post_id] [int] IDENTITY NOT NULL primary key,
	[comment_post_id] [int],
	[user_id] [int],
    [date_rep_commentpost] [datetime],
	[text_rep_commentpost][nvarchar](MAX),
	FOREIGN KEY (comment_post_id) REFERENCES [CommentPost](comment_post_id),
	FOREIGN KEY (user_id) REFERENCES [User](user_id)
)
/*==============================================================*/
/* Table: SavePost                                        */
/*==============================================================*/
CREATE TABLE [dbo].[SavePost](
    [save_id] [int] IDENTITY NOT NULL primary key,
	[post_id] [int],
	[user_id] [int],
	[name_save] [NVARCHAR](255),
    [date_save] [datetime],
	FOREIGN KEY (post_id) REFERENCES [Posts](post_id)
)
/*==============================================================*/
/* Table: LevelCourse                                    */
/*==============================================================*/
CREATE TABLE [dbo].[LevelCourse](
    [lc_id] [int] IDENTITY NOT NULL primary key,
    [level_name] [nvarchar] (255),
	
)
/*==============================================================*/
/* Table: GeneralCourse                                        */
/*==============================================================*/
CREATE TABLE [dbo].[GeneralCourse](
    [general_course_id] [int] IDENTITY NOT NULL primary key,
	[lc_id] [int] ,
	[manager_id] [int],
	[name_general_course] [nvarchar](500),
	[des_general_course] [nvarchar](2000),
	[price_course] [int],
    [create_date] [datetime],
	FOREIGN KEY (lc_id) REFERENCES [LevelCourse](lc_id)
)
/*==============================================================*/
/* Table: Course                                        */
/*==============================================================*/
CREATE TABLE [dbo].[Course](
    [course_id] [int] IDENTITY NOT NULL primary key,
	[general_course_id] [int] ,
	[name_course] [nvarchar](500),
	[des_course] [nvarchar](1000),
    [create_date] [datetime],
	FOREIGN KEY (general_course_id) REFERENCES [GeneralCourse](general_course_id),
	)
/*==============================================================*/
/* Table: VoteCourse                                       */
/*==============================================================*/
CREATE TABLE [dbo].[VoteGeneralCourse](
    [vote_course_id] [int] IDENTITY NOT NULL primary key,
	[general_course_id] [int],
	[user_id] [int],
    [date_vote] [datetime],
	[number_star] [int],
	FOREIGN KEY (general_course_id) REFERENCES [GeneralCourse](general_course_id),
	FOREIGN KEY (user_id) REFERENCES [User](user_id)
)
/*==============================================================*/
/* Table: ListStudent                                        */
/*==============================================================*/
CREATE TABLE [dbo].[ListStudent](
	[user_id] [int] not null,
	[general_course_id] [int] not null,
	[start_date] [datetime],
	[end_date] [datetime],
PRIMARY KEY (user_id,general_course_id),
  FOREIGN KEY (user_id) REFERENCES [User](user_id),
  FOREIGN KEY (general_course_id) REFERENCES [GeneralCourse](general_course_id)
)
/*==============================================================*/
/* Table: ListTeacher                                        */
/*==============================================================*/
CREATE TABLE [dbo].[ListTeacher](
	[teacher_id] [int] not null,
	[general_course_id] [int] not null,
PRIMARY KEY (teacher_id,general_course_id),
  FOREIGN KEY (teacher_id) REFERENCES [User](user_id),
  FOREIGN KEY (general_course_id) REFERENCES [GeneralCourse](general_course_id)
)

/*==============================================================*/
/* Table: Lesson                                      */
/*==============================================================*/
CREATE TABLE [dbo].[Lesson](
    [lesson_id] [int] IDENTITY NOT NULL primary key,
	[course_id] [int],
	[teacher_id] [int],
    [video_lesson] [varchar](100),
	[time_video] [time],
	[date_lesson] [datetime],
	[des_lesson] [nvarchar](3000),
	FOREIGN KEY (course_id) REFERENCES [Course](course_id)
)
/*==============================================================*/
/* Table: Lesson                                      */
/*==============================================================*/
CREATE TABLE [dbo].[LearningProcess](
    [lesson_id] [int],
	[user_id] [int],
	PRIMARY KEY(lesson_id,user_id),
	FOREIGN KEY (lesson_id) REFERENCES [Lesson](lesson_id),
	FOREIGN KEY (user_id) REFERENCES [User](user_id),
)
/*==============================================================*/
/* Table: CommentLesson                                      */
/*==============================================================*/
CREATE TABLE [dbo].[CommentLesson](
    [comment_lesson_id] [int] IDENTITY NOT NULL primary key,
	[lesson_id] [int],
	[user_id] [int],
    [date_comment_lesson] [datetime],
	[text_comment_lesson][nvarchar](MAX),
	FOREIGN KEY (lesson_id) REFERENCES [Lesson](lesson_id),
	FOREIGN KEY (user_id) REFERENCES [User](user_id)
)
/*==============================================================*/
/* Table: RepCommentLesson                                      */
/*==============================================================*/
CREATE TABLE [dbo].[RepCommentLesson](
    [rep_comment_lesson_id] [int] IDENTITY NOT NULL primary key,
	[comment_lesson_id] [int],
	[user_id] [int],
    [date_rep_comment_lesson] [datetime],
	[text_rep_comment_lesson][nvarchar](MAX),
	FOREIGN KEY (comment_lesson_id) REFERENCES [CommentLesson](comment_lesson_id),
	FOREIGN KEY (user_id) REFERENCES [User](user_id)
)
/*==============================================================*/
/* Table: FinalExam                                      */
/*==============================================================*/
CREATE TABLE [dbo].[FinalExam](
    [final_exam_id] [int] IDENTITY NOT NULL primary key,
	[general_course_id] [int],
	[teacher_id] [int],
    [date_open_exam] [datetime],
	[content_exam][nvarchar](MAX),
	FOREIGN KEY (general_course_id) REFERENCES [GeneralCourse](general_course_id)
)
/*==============================================================*/
/* Table: Test                                    */
/*==============================================================*/
CREATE TABLE [dbo].[Test](
    [test_id] [int] IDENTITY NOT NULL primary key,
	[final_exam_id] [int],
	[user_id] [int],
	[teacher_id] [int],
    [deadline_submit] [datetime],
	[link_video][varchar](100),
	[des][varchar](1000),
	[test_score] [int],
	[status] [nvarchar] (50),
	FOREIGN KEY (final_exam_id) REFERENCES [FinalExam](final_exam_id),
	FOREIGN KEY (user_id) REFERENCES [User](user_id)
)
/*==============================================================*/
/* Table: PayMent                                   */
/*==============================================================*/
CREATE TABLE [dbo].[PayMent](
    [pay_ment_id] [int] IDENTITY NOT NULL primary key,
	[general_course_id] [int],
	[user_id] [int],
	[status_payment] [nvarchar] (50),
	FOREIGN KEY (general_course_id) REFERENCES [GeneralCourse](general_course_id),
	FOREIGN KEY (user_id) REFERENCES [User](user_id)
)
/*==============================================================*/
/* Table: Certification                                   */
/*==============================================================*/
CREATE TABLE [dbo].[Certification](
	[general_course_id] [int],
	[user_id] [int],
	[date_Cer] [datetime],
	[text_certification] [nvarchar] (500),
	Primary key (general_course_id,user_id),
	FOREIGN KEY (general_course_id) REFERENCES [GeneralCourse](general_course_id),
	FOREIGN KEY (user_id) REFERENCES [User](user_id)
)