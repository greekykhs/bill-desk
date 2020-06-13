----------------------------------------------------------------------------
##Assumptions

Login:-
1). User details are already present in user_details.
2). The user will login using email id.
3). Password in user_details is encrypted. As of now I am not doing any encryption/ decryption, I am just send the password received in the API to the database for comparison.
4). is_active signifies whether user is active or not. As of now we are not checking it.
5). Two user's Gabriel and Michael are already created. Their email id's are gabriel@gmail.com and michael@gmail.com, and password is 'qwert'.
6). Michael is a Admin user and is allowed to view report, since Gabriel is a normal user he won't be able to access the report.

Reports:-
1). To view the report login is mandatory.
2). Only the users with admin roles will be allowed to fetch the reports. Roles can be configured in user_role column of user_details.
3). As of now only one report is available, which fetch the transaction details for a date range.

Transaction:-
1). User can make the payment with or without login.
2). Non-logged in user need to enter mobile no. to proceed with the payment.
3). I assumed there is an abstract payment web-service which we need to call.
4). 'mode' is used to specify which payment mode user has selected e.g: Debit Card, Net Banking, e-wallet etc. As of now the mode is not used any where, we are just saving it in DB.
5). When the payment is initiated the transaction details are inserted in transaction_details with status as 'In Progress'. Also the transaction_audittrail is inserted. Depending on the output of third party payment webserice, the transaction status is updated in transaction_details, also the transaction_audittrail is inserted. In case of exception which updating transaction_details, we can retry or write a cron which we fetch updated status from the third party payment webserice and update the details in our DB.

JWT:-
1). The JWT token is generated with a hard-coded key.
2). The JWT token expiration time is 10 hours (which is also hard-coded).
3). The payload contains a pipe separated email id and mobile no.

Merchants:-
1). merchants table contains available bill payment services like mobile bill pay, gas bill or electricity bill.
2). merchant_details contains unique merchant_key, which will be sent to the payment service (which I assumed exist). Based on the merchant_key, the service will return a page, which will be shown to the user (I assumed payment is done without this).
----------------------------------------------------------------------------
##Future Enhancements
1). Web-service to create and update users, update user roles.
2). Functionality for admin users to activate/deactivate the users.
3). Password change policy for the users.
4). User who are not logged in for x number of days will be deactivated.
5). Forgot password functionality.
6). Web-service to add, update, delete new merchants.
7). Caching.
8). Purging the audit trail table or the transaction table (if required) after x number of years.
9). Adding Spring security.
10). As of now the reports can be fetched for the date range, we can provide more options to fetch the filtered report.
11). Deploy the transaction web-service separately.
12). Sending mobile notifications (in case of app), emails and SMS or via the telegram app after the transaction or when the transaction status is updated.
13). Adding more unit test cases.
14). Better unit test cases can be written using Mockito.
----------------------------------------------------------------------------
DB Details:
URL: http://www.phpmyadmin.co/
Server: sql12.freemysqlhosting.net
Name: sql12347910
Username: sql12347910
Password: fuNTnzts6E
----------------------------------------------------------------------------
create table user_details (user_id bigint not null, created_on datetime, email varchar(255), is_active varchar(255), mobile_no varchar(255), password varchar(255), updated_on datetime, user_name varchar(255), user_role varchar(255), primary key (user_id))

create table merchant_details (id integer not null, merchant_key varchar(255), merchant_subtype varchar(255), merchant_type varchar(255), merchant_mid integer, primary key (id))

create table merchants (mid integer not null, merchant_name varchar(255), merchant_type varchar(255), primary key (mid))

create table transaction_audittrail (at_id bigint not null, txn_date datetime, txn_id bigint, txn_remark varchar(255), txn_status varchar(255), primary key (at_id))

create table transaction_details (txn_id bigint not null, amount varchar(255), is_partial varchar(255), merchant_key varchar(255), mobile_no varchar(255), txn_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP, txn_mode varchar(255), txn_remark varchar(255), txn_status varchar(255), user_id varchar(255), primary key (txn_id))
----------------------------------------------------------------------------
INSERT INTO `merchants` (`mid`, `merchant_type`, `merchant_name`) VALUES ('1', UPPER('MOB'), UPPER('Mobile'));
INSERT INTO `merchants` (`mid`, `merchant_type`, `merchant_name`) VALUES ('2', UPPER('GAS'), UPPER('GAS'));
INSERT INTO `merchants` (`mid`, `merchant_type`, `merchant_name`) VALUES ('3', UPPER('ELE'), UPPER('ELECTRICITY'));

INSERT INTO `merchant_details` (`id`, `merchant_key`, `merchant_subtype`, `merchant_type`, `merchant_mid`) VALUES (ABS('0'), 'MOB_VOD', 'Vodafone', 'MOB', '1');
INSERT INTO `merchant_details` (`id`, `merchant_key`, `merchant_subtype`, `merchant_type`, `merchant_mid`) VALUES (ABS('1'), 'MOB_ID', 'Idea', 'MOB', '1');
INSERT INTO `merchant_details` (`id`, `merchant_key`, `merchant_subtype`, `merchant_type`, `merchant_mid`) VALUES (ABS('2'), 'MOB_JIO', 'Jio', 'MOB', '1');
INSERT INTO `merchant_details` (`id`, `merchant_key`, `merchant_subtype`, `merchant_type`, `merchant_mid`) VALUES (ABS('3'), 'GAS_BH', 'Bharat Gas', 'GAS', '2');
INSERT INTO `merchant_details` (`id`, `merchant_key`, `merchant_subtype`, `merchant_type`, `merchant_mid`) VALUES (ABS('4'), 'ELE_AD', 'Adani', 'ELE', '3');

INSERT INTO `user_details` (`user_id`, `created_on`, `email`, `is_active`, `mobile_no`, `password`, `updated_on`, `user_name`, `user_role`) VALUES ('1', NOW(), 'gabriel@gmail.com', 'Y', '9999999999', 'qwert', NOW(), 'Archangel Gabriel', 'USER');

INSERT INTO `user_details` (`user_id`, `created_on`, `email`, `is_active`, `mobile_no`, `password`, `updated_on`, `user_name`, `user_role`) VALUES ('2', NOW(), 'michael@gmail.com', 'Y', '8888888888', 'qwert', NOW(), 'Archangel Michael ', 'ADMIN');
----------------------------------------------------------------------------