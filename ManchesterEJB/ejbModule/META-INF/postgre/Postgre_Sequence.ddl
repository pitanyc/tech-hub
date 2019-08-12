DROP SEQUENCE public.access_seq;
DROP SEQUENCE public.difficulty_seq;
DROP SEQUENCE public.locale_seq;
DROP SEQUENCE public.priority_seq;
DROP SEQUENCE public.project_seq;
DROP SEQUENCE public.task_seq;
DROP SEQUENCE public.taskstatus_seq;
DROP SEQUENCE public.tasktype_seq;
DROP SEQUENCE public.user_seq;
DROP SEQUENCE public.usertype_seq;

CREATE SEQUENCE public.access_seq INCREMENT 1 START 1000 MINVALUE 0;
CREATE SEQUENCE public.difficulty_seq INCREMENT 1 START 1000 MINVALUE 0;
CREATE SEQUENCE public.locale_seq INCREMENT 1 START 1000 MINVALUE 0;
CREATE SEQUENCE public.priority_seq INCREMENT 1 START 1000 MINVALUE 0;
CREATE SEQUENCE public.project_seq INCREMENT 1 START 1000 MINVALUE 0;
CREATE SEQUENCE public.task_seq INCREMENT 1 START 1000 MINVALUE 0;
CREATE SEQUENCE public.taskstatus_seq INCREMENT 1 START 1000 MINVALUE 0;
CREATE SEQUENCE public.tasktype_seq INCREMENT 1 START 1000 MINVALUE 0;
CREATE SEQUENCE public.user_seq INCREMENT 1 START 1000 MINVALUE 0;
CREATE SEQUENCE public.usertype_seq INCREMENT 1 START 1000 MINVALUE 0;
