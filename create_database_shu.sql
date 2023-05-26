/* DB作成 */
DROP DATABASE IF EXISTS task_db;
CREATE DATABASE task_db CHARACTER SET utf8 COLLATE utf8_general_ci;

/* DB選択 */
USE task_db;

/* ユーザマスタ作成 */
CREATE TABLE task_db.m_user
(
	user_id VARCHAR(24) NOT NULL,
	password VARCHAR(64) NOT NULL,
	user_name VARCHAR(20) UNIQUE NOT NULL,
	update_datetime TIMESTAMP DEFAULT current_timestamp ON UPDATE current_timestamp NOT NULL,
	PRIMARY KEY (user_id)
);

/* カテゴリマスタ作成 */
CREATE TABLE task_db.m_category
(
	category_id INT NOT NULL AUTO_INCREMENT,
	category_name VARCHAR(20) UNIQUE NOT NULL,
	update_datetime TIMESTAMP DEFAULT current_timestamp ON UPDATE current_timestamp NOT NULL,
	PRIMARY KEY (category_id)
);

/* ステータスマスタ作成 */
CREATE TABLE task_db.m_status
(
	status_code     CHAR(2) NOT NULL ,
	status_name     VARCHAR(20) UNIQUE NOT NULL,
	update_datetime TIMESTAMP DEFAULT current_timestamp ON UPDATE current_timestamp NOT NULL,
	PRIMARY KEY (status_code)
);

/* タスクテーブル作成 */
CREATE TABLE task_db.t_task
(
	task_id INT NOT NULL AUTO_INCREMENT,
	task_name VARCHAR(50) NOT NULL,
	category_id INT NOT NULL,
	limit_date DATE,
	user_id VARCHAR(24) NOT NULL,
	status_code CHAR(2) NOT NULL,
	memo VARCHAR(100),
	create_datetime TIMESTAMP DEFAULT current_timestamp NOT NULL,
	update_datetime TIMESTAMP DEFAULT current_timestamp ON UPDATE current_timestamp NOT NULL,
	PRIMARY KEY (task_id),
	FOREIGN KEY (category_id) REFERENCES m_category(category_id),
	FOREIGN KEY (user_id) REFERENCES m_user(user_id),
	FOREIGN KEY (status_code) REFERENCES m_status(status_code)
);

/* ユーザマスタINSERT */
INSERT INTO task_db.m_user (user_id, password, user_name) VALUES ('admin', '2e7d01869cd65058cb884c7c039b804bc76d2a259f6143e84a86980e7cdfe23a', '桑原');

/* カテゴリマスタINSERT */
INSERT INTO task_db.m_category (category_name) VALUES ('新商品A：開発プロジェクト');
INSERT INTO task_db.m_category (category_name) VALUES ('既存商品B：改良プロジェクト');

/* ステータスマスタINSERT */
INSERT INTO task_db.m_status (status_code, status_name) VALUES ('00', '未着手');
INSERT INTO task_db.m_status (status_code, status_name) VALUES ('50', '着手');
INSERT INTO task_db.m_status (status_code, status_name) VALUES ('99', '完了');
