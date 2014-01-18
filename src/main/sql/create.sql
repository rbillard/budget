-- MySQL Database
create table L_PERIOD_BUDGET (amount numeric(19,2), T_PERIOD_ID bigint, T_BUDGET_ID bigint, primary key (T_BUDGET_ID, T_PERIOD_ID));
create table T_BUDGET (id bigint NOT NULL AUTO_INCREMENT, label varchar(255) not null, primary key (id));
create table T_OPERATION (id bigint NOT NULL AUTO_INCREMENT, amount numeric(19,2) not null, date timestamp not null, label varchar(255) not null, T_BUDGET_ID bigint not null, primary key (id));
create table T_PERIOD (id bigint NOT NULL AUTO_INCREMENT, endDate timestamp not null, label varchar(255) not null, startDate timestamp not null, T_USER_ID bigint not null, primary key (id));
create table T_USER (id bigint NOT NULL AUTO_INCREMENT, login varchar(255) not null, password varchar(255) not null, primary key (id));
alter table L_PERIOD_BUDGET add constraint FK_7w0kr36t1lwqkaa7far1e43ea foreign key (T_PERIOD_ID) references T_PERIOD ( ID );
alter table L_PERIOD_BUDGET add constraint FK_ne7q8d0o5xo8s4dgsk6xkgy8x foreign key (T_BUDGET_ID) references T_BUDGET ( ID );
alter table T_OPERATION add constraint FK_4fc9kd9yi8voyebqh7wpupxd3 foreign key (T_BUDGET_ID) references T_BUDGET ( ID );
alter table T_PERIOD add constraint FK_oh1t92uaipj8i65pgf7g2f1ex foreign key (T_USER_ID) references T_USER ( ID );