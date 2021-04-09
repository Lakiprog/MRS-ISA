insert into pharmacy (name, adress, city, country, description, rating) values ('Dinuteka', 'Promenada1', 'NS', 'RS', 'Apoteka koja razbija glavu', 5);
insert into pharmacy (name, adress, city, country, description, rating) values ('Drusoteka', 'Promenada3', 'BG', 'DE', 'Apoteka koja leci glavu', 2);

insert into user (USER_TYPE, email, name, surname, adress, city, country, phone_number, username, password, first_login, rating) values ('PATIENT', 'kaki@gmail.com', 'Marko', 'Marković', 'Negde69', 'NS', 'Srbija', '060602311', 'kaki', 'kaki', null, null);
insert into user (USER_TYPE, email, name, surname, adress, city, country, phone_number, username, password, first_login, rating) values ('PATIENT', 'haker@gmail.com', 'Petar', 'Markuza', 'Negde420', 'NS', 'Srbija', '060602313', 'kek', 'keki', null, null);
insert into user (USER_TYPE, email, name, surname, adress, city, country, phone_number, username, password, first_login, rating) values ('PATIENT', 'hakering@gmail.com', 'Dinu', 'Dinu', 'Negde1', 'NS', 'Srbija', '060622311', 'kul', 'kul', null, null);
insert into user (USER_TYPE, email, name, surname, adress, city, country, phone_number, username, password, first_login, rating) values ('PATIENT', 'keter@gmail.com', 'Ikea', 'Velika', 'Negde42', 'BG', 'Srbija', '061602311', 'Keter', 'Keter', null, null);

insert into user (USER_TYPE, email, name, surname, adress, city, country, phone_number, username, password, first_login, rating) values ('PHARMACIST', 'farmaceut@gmail.com', 'Micko', 'Mica', 'Telep69', 'NS', 'Srbija', '0620602311', 'farmaceut', 'farmaceut', false, 1);
insert into user (USER_TYPE, email, name, surname, adress, city, country, phone_number, username, password, first_login, rating) values ('DERMATOLOGIST', 'dermatolog@gmail.com', 'Petar', 'Markuza', 'Telep420', 'NS', 'Srbija', '060602313', 'dermatolog', 'dermatolog', false, 5);

insert into user (USER_TYPE, email, name, surname, adress, city, country, phone_number, username, password, pharmacy_id) values ('PHARMACY_ADMIN', 'first@gmail.com', 'Pera', 'Peric', 'asdf', 'NS', 'Srbija', '0601234567', 'pharmacyAdmin1', '123', null);
insert into user (USER_TYPE, email, name, surname, adress, city, country, phone_number, username, password, pharmacy_id) values ('PHARMACY_ADMIN', 'second@gmail.com', 'Mika', 'Mikic', 'asdf', 'NS', 'Srbija', '0601234568', 'pharmacyAdmin2', '123', null);
insert into user (USER_TYPE, email, name, surname, adress, city, country, phone_number, username, password, pharmacy_id) values ('PHARMACY_ADMIN', 'third@gmail.com', 'Sara', 'Saric', 'asdf', 'NS', 'Srbija', '0601234569', 'pharmacyAdmin3', '123', null);



insert into employment (EMPLOYMENT_TYPE, end, start, pharmacy_id, pharmacist_id) values ('EMPLOYMENT_PHARMACIST', 20, 9, 1, 5);
insert into employment (EMPLOYMENT_TYPE, end, start, pharmacy_id, dermatologist_id) values ('EMPLOYMENT_DERMATOLOGIST', 20, 8, 1, 6);
insert into employment (EMPLOYMENT_TYPE, end, start, pharmacy_id, dermatologist_id) values ('EMPLOYMENT_DERMATOLOGIST', 19, 9, 2, 6);

insert into appointment (APPOINTMENT_TYPE, start, end, price, patient_id, pharmacist_id, dermatologist_id, pharmacy_id) values ('APPOINTMENT_PHARMACIST', '2021-01-22 18:30:00', '2021-01-22 19:30:00', 1000, 1, 5, null, 1);
insert into appointment (APPOINTMENT_TYPE, start, end, price, patient_id, pharmacist_id, dermatologist_id, pharmacy_id) values ('APPOINTMENT_PHARMACIST', '2021-01-22 19:45:00', '2021-01-22 20:30:00', 1000, 1, 5, null, 1);
insert into appointment (APPOINTMENT_TYPE, start, end, price, patient_id, pharmacist_id, dermatologist_id, pharmacy_id) values ('APPOINTMENT_PHARMACIST', '2021-01-23 12:30:00', '2021-01-22 17:30:00', 1000, 2, 5, null, 1);
insert into appointment (APPOINTMENT_TYPE, start, end, price, patient_id, pharmacist_id, dermatologist_id, pharmacy_id) values ('APPOINTMENT_PHARMACIST', '2021-02-22 18:30:00', '2021-01-22 19:30:00', 1000, 3, 5, null, 1);

insert into appointment (APPOINTMENT_TYPE, start, end, price, patient_id, pharmacist_id, dermatologist_id, pharmacy_id) values ('APPOINTMENT_DERMATOLOGIST', '2021-01-22 16:30:00', '2021-01-22 18:30:00', 1000, 1, null, 6, 1);
insert into appointment (APPOINTMENT_TYPE, start, end, price, patient_id, pharmacist_id, dermatologist_id, pharmacy_id) values ('APPOINTMENT_DERMATOLOGIST', '2021-02-22 9:30:00', '2021-01-22 10:00', 1000, 4, null, 6, 2);

insert into appointment (APPOINTMENT_TYPE, start, end, price, patient_id, pharmacist_id, dermatologist_id, pharmacy_id) values ('APPOINTMENT_DERMATOLOGIST', '2021-05-22 16:30:00', '2021-01-22 18:30:00', 1000, null, null, 6, 1);
insert into appointment (APPOINTMENT_TYPE, start, end, price, patient_id, pharmacist_id, dermatologist_id, pharmacy_id) values ('APPOINTMENT_DERMATOLOGIST', '2021-06-22 9:30:00', '2021-01-22 10:00', 1000, null, null, 6, 2);

insert into absence (description, start, end, approved, doctor_id) values ("Ocu kuci!", '2021-06-10 16:30:00', '2021-06-10 19:30:00', true, 5);
insert into absence (description, start, end, approved, doctor_id) values ("Ocu kuci!", '2021-06-15 9:30:00', '2021-06-20 19:30:00', false, 5);
insert into absence (description, start, end, approved, doctor_id) values ("Ocu kuci!", '2021-06-15 9:30:00', '2021-06-19 19:30:00', true, 5);
insert into absence (description, start, end, approved, doctor_id) values ("Ocu kuci!", '2021-06-15 9:30:00', '2021-06-20 19:30:00', true, 6);

insert into medicine (medicine_code, name, medicine_type, form, composition, manufacturer, prescription, addtional_comments) 
	values ('S123', 'medicine1', 'headache', 'pill', 'random stuff', 'mrsisa', true, 'comment');
insert into medicine (medicine_code, name, medicine_type, form, composition, manufacturer, prescription, addtional_comments) 
	values ('S124', 'medicine2', 'headache', 'pill', 'random stuff', 'mrsisa', false, 'comment');
insert into medicine (medicine_code, name, medicine_type, form, composition, manufacturer, prescription, addtional_comments) 
	values ('S125', 'medicine3', 'headache', 'pill', 'random stuff', 'mrsisa', true, null);
	

insert into substitute_medicine (medicine_id, substitute_medicine_id) values (1, 2);
insert into substitute_medicine (medicine_id, substitute_medicine_id) values (1, 3);
insert into substitute_medicine (medicine_id, substitute_medicine_id) values (2, 1);

insert into allergy (medicine_id, patient_id) values (1, 3);
insert into allergy (medicine_id, patient_id) values (2, 2);

insert into medicine_pharmacy (cost, amount, pharmacy_id, medicine_id) values (500, 1000, 1, 1);
insert into medicine_pharmacy (cost, amount, pharmacy_id, medicine_id) values (600, 400, 1, 2);
insert into medicine_pharmacy (cost, amount, pharmacy_id, medicine_id) values (1000, 50, 1, 3);
insert into medicine_pharmacy (cost, amount, pharmacy_id, medicine_id) values (500, 500, 2, 1);
insert into medicine_pharmacy (cost, amount, pharmacy_id, medicine_id) values (800, 800, 2, 2);