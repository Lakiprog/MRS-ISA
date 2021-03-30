insert into user (USER_TYPE, email, name, surname, adress, city, country, phone_number, username, password, first_login, rating) values ('PATIENT', 'kaki@gmail.com', 'Marko', 'Marković', 'Negde69', 'NS', 'Srbija', '060602311', 'kaki', 'kaki', null, null);
insert into user (USER_TYPE, email, name, surname, adress, city, country, phone_number, username, password, first_login, rating) values ('PATIENT', 'haker@gmail.com', 'Petar', 'Markuza', 'Negde420', 'NS', 'Srbija', '060602313', 'kek', 'keki', null, null);
insert into user (USER_TYPE, email, name, surname, adress, city, country, phone_number, username, password, first_login, rating) values ('PATIENT', 'hakering@gmail.com', 'Dinu', 'Dinu', 'Negde1', 'NS', 'Srbija', '060622311', 'kul', 'kul', null, null);
insert into user (USER_TYPE, email, name, surname, adress, city, country, phone_number, username, password, first_login, rating) values ('PATIENT', 'keter@gmail.com', 'Ikea', 'Velika', 'Negde42', 'BG', 'Srbija', '061602311', 'Keter', 'Keter', null, null);

insert into user (USER_TYPE, email, name, surname, adress, city, country, phone_number, username, password, first_login, rating) values ('PHARMACIST', 'farmaceut@gmail.com', 'Micko', 'Mica', 'Telep69', 'NS', 'Srbija', '0620602311', 'farmaceut', 'farmaceut', false, 1);
insert into user (USER_TYPE, email, name, surname, adress, city, country, phone_number, username, password, first_login, rating) values ('DERMATOLOGIST', 'dermatolog@gmail.com', 'Petar', 'Markuza', 'Telep420', 'NS', 'Srbija', '060602313', 'dermatolog', 'dermatolog', false, 5);

insert into appointment (APPOINTMENT_TYPE, start, end, price, patient_id, pharmacist_id, dermatologist_id) values ('APPOINTMENT_PHARMACIST', '2021-01-22 18:30:00', '2021-01-22 19:30:00', 1000, 1, 5, null);
insert into appointment (APPOINTMENT_TYPE, start, end, price, patient_id, pharmacist_id, dermatologist_id) values ('APPOINTMENT_PHARMACIST', '2021-01-23 12:30:00', '2021-01-22 17:30:00', 1000, 2, 5, null);
insert into appointment (APPOINTMENT_TYPE, start, end, price, patient_id, pharmacist_id, dermatologist_id) values ('APPOINTMENT_PHARMACIST', '2021-02-22 18:30:00', '2021-01-22 19:30:00', 1000, 3, 5, null);

insert into appointment (APPOINTMENT_TYPE, start, end, price, patient_id, pharmacist_id, dermatologist_id) values ('APPOINTMENT_DERMATOLOGIST', '2021-01-22 16:30:00', '2021-01-22 18:30:00', 1000, 1, null, 6);
insert into appointment (APPOINTMENT_TYPE, start, end, price, patient_id, pharmacist_id, dermatologist_id) values ('APPOINTMENT_DERMATOLOGIST', '2021-02-22 9:30:00', '2021-01-22 10:00', 1000, 4, null, 6);