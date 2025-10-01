
create database company_db;
use  company_db;
CREATE TABLE Department(
	dept_id INT PRIMARY KEY,
    dept_name VARCHAR(50) NOT NULL
);
insert into Department values
(1, 'HR'),
(2, 'Engineering'),
(3, 'Finance'),
(4, 'Marketing');

CREATE TABLE Employee(
	emp_id INT PRIMARY KEY,
    emp_name VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE,
    dept_id INT,
    hire_date DATE,
    salary DECIMAL(10,2),
    FOREIGN KEY(dept_id) REFERENCES Department(dept_id)
);
alter table Employee rename column name to emp_name;

INSERT INTO Employee(emp_id, emp_name, email, dept_id, hire_date, salary) VALUES
(101, 'Alice Johnson', 'alice@example.com', 2, '2022-03-15', 70000),
(102, 'Bob Smith', 'bob@example.com', 1, '2021-07-23', 50000),
(103, 'Charlie Lee', 'charlie@example.com', 2, '2023-01-10', 80000),
(104, 'Diana Prince', 'diana@example.com', 3, '2020-11-05', 65000),
(105, 'Evan Brown', 'evan@example.com', 4, '2022-06-18', 55000);

CREATE TABLE Projects (
	project_id INT PRIMARY KEY,
    project_name VARCHAR(100) NOT NULL,
    dept_id INT,
    start_date DATE,
    end_date DATE,
    FOREIGN KEY (dept_id) references Department(dept_id)
);

INSERT INTO Projects (project_id, project_name, dept_id, start_date, end_date) VALUES
(201, 'Website Redesign', 4, '2023-01-01', '2023-06-30'),
(202, 'Payroll System', 3, '2022-05-01', '2022-12-31'),
(203, 'AI Research', 2, '2023-03-15', '2024-03-15');

CREATE table skills(
	skill_id INT PRIMARY KEY,
    skill VARCHAR(50)
);
alter table employee add foreign key (skill) references Skills(skill_id); 
drop table skills;
show create table employee;
-- we require to delete the constraint first to drop column
alter table employee drop foreign key employee_ibfk_2;
alter table employee drop column skill;

select * from department;
-- update the table
UPDATE Department SET dept_name = "Human Resource" where dept_id = 1;

delete from employee where emp_id = 101;

insert into employee values (101, 'Alice Johnson', 'alice@example.com', 2, '2022-03-15', 70000);
SET autocommit = 0;
start transaction;
	delete from employee where emp_id = 101;
select * from employee;
rollback;
commit;

update employee set salary = salary*1.10 where dept_id = 2;
select emp_name,salary from employee order by salary and dept_id desc;

select dept_id ,count(*) from employee group by dept_id;
-- DQL
select * from employee where hire_date >= "2022-01-01" order by hire_date;
select dept_id,count(*) from employee group by dept_id having count(*) > 1;
select avg(salary) from employee group by dept_id having dept_id = 2;
select emp_name,salary from employee where salary between 70000 and 100000 order by emp_name;
select dept_id ,sum(salary)from employee group by dept_id having sum(salary)>70000;
select dept_id ,count(emp_id) from employee group by dept_id having avg(salary) > 60000;
select dept_id ,max(salary) from employee  group by dept_id;
select emp_name from employee where emp_name like 'B%' and salary > 40000;
-- joins
select e.emp_name,d.dept_name from employee e inner join department d on e.dept_id = d.dept_id;
select e.emp_name from employee e left join department d on e.dept_id = d.dept_id where e.dept_id is null;
select d.dept_name from employee e right join department d on e.dept_id = d.dept_id where e.dept_id is null;
select e.emp_id, e.emp_name, d.dept_name
from employee e
left join department d on e.dept_id = d.dept_id
union
select e.emp_id, e.emp_name, d.dept_name
from employee e
right join department d on e.dept_id = d.dept_id;

select d.dept_name,sum(e.salary) from department d right join employee e on d.dept_id =e.dept_id group by d.dept_id; 

-- view
create view emp_with_dept as select e.emp_name,d.dept_name from employee e inner join department d on e.dept_id = d.dept_id;
create view avg_salary_dept as select avg(salary) as avg_sal , d.dept_name from employee e right join department d on e.dept_id = d.dept_id group by d.dept_name; 
select * from avg_salary_dept where avg_sal>60000;
drop view emp_with_dept;

-- procedures
delimiter //
create procedure AddEmployee(
	IN e_id INT ,
    IN e_name VARCHAR(100),
    IN eml VARCHAR(100),
    IN id INT,
    IN h_date DATE,
    IN e_salary DECIMAL(10,2)
  )
  begin
  INSERT INTO Employee(emp_id, emp_name, email, dept_id, hire_date, salary) VALUES
	(e_id, e_name, eml,id,h_date,e_salary);
end //
delimiter ;
drop procedure AddEmployee;
call AddEmployee(108,"rohan","rohan@gmail.com",3,"2023-9-2",30000);

-- functions
delimiter //
create function GetAnnualSalary(mon_salary decimal(10,2))
returns decimal(10,2)
deterministic
begin
	return mon_salary*12;
end //
delimiter ;
select emp_name , GetAnnualSalary(salary) as anul_salary from employee;

CREATE USER 'developer'@'localhost' identified by '123';
grant select, update on company_db.* to 'developer'@'localhost';
flush privileges;
revoke update on company_db.* from 'developer'@'localhost';

