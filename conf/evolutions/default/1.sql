# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table account (
  id                        bigint auto_increment not null,
  account_number            bigint,
  balance                   integer,
  constraint uq_account_account_number unique (account_number),
  constraint pk_account primary key (id))
;

create table bangking_transaction (
  id                        bigint auto_increment not null,
  amount                    integer,
  sender_account_id         bigint,
  receiver_account_id       bigint,
  constraint pk_bangking_transaction primary key (id))
;

alter table bangking_transaction add constraint fk_bangking_transaction_senderAccount_1 foreign key (sender_account_id) references account (id) on delete restrict on update restrict;
create index ix_bangking_transaction_senderAccount_1 on bangking_transaction (sender_account_id);
alter table bangking_transaction add constraint fk_bangking_transaction_receiverAccount_2 foreign key (receiver_account_id) references account (id) on delete restrict on update restrict;
create index ix_bangking_transaction_receiverAccount_2 on bangking_transaction (receiver_account_id);



# --- !Downs

SET FOREIGN_KEY_CHECKS=0;

drop table account;

drop table bangking_transaction;

SET FOREIGN_KEY_CHECKS=1;

