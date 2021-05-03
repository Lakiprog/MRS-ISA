insert into pharmacy (name, address, city, country, description, rating, appointment_price) values ('Dinuteka', 'Promenada1', 'NS', 'RS', 'Apoteka koja razbija glavu', 5, 1000.0);
insert into pharmacy (name, address, city, country, description, rating, appointment_price) values ('Drusoteka', 'Promenada3', 'BG', 'DE', 'Apoteka koja leci glavu', 2, 800.0);

insert into user (USER_TYPE, email, name, surname, address, city, country, phone_number, username, password, first_login, rating, enabled) values ('PATIENT', 'mrs_isa_2021_t15@hotmail.com', 'Marko', 'Marković', 'Negde69', 'NS', 'Srbija', '0606023113', 'laki', '$2y$12$qX83UfVgBd7NoAiuxEkHnOtloUpaSNvUq3OfFlS8fBetoqI91aRxO', null, null, true);
insert into user (USER_TYPE, email, name, surname, address, city, country, phone_number, username, password, first_login, rating, enabled) values ('PATIENT', 'haker@gmail.com', 'Petar', 'Markuza', 'Negde420', 'NS', 'Srbija', '0606023132', 'kek', '$2y$12$qX83UfVgBd7NoAiuxEkHnOtloUpaSNvUq3OfFlS8fBetoqI91aRxO', null, null, true);
insert into user (USER_TYPE, email, name, surname, address, city, country, phone_number, username, password, first_login, rating, enabled) values ('PATIENT', 'hakering@gmail.com', 'Dinu', 'Dinu', 'Negde1', 'NS', 'Srbija', '0606223112', 'kul', '$2y$12$qX83UfVgBd7NoAiuxEkHnOtloUpaSNvUq3OfFlS8fBetoqI91aRxO', null, null, true);
insert into user (USER_TYPE, email, name, surname, address, city, country, phone_number, username, password, first_login, rating, enabled) values ('PATIENT', 'keter@gmail.com', 'Ikea', 'Velika', 'Negde42', 'BG', 'Srbija', '0616023113', 'Keter', '$2y$12$qX83UfVgBd7NoAiuxEkHnOtloUpaSNvUq3OfFlS8fBetoqI91aRxO', null, null, true);

insert into user (USER_TYPE, email, name, surname, address, city, country, phone_number, username, password, first_login, rating, enabled) values ('PHARMACIST', 'farmaceut@gmail.com', 'Micko', 'Mica', 'Telep69', 'NS', 'Srbija', '0620602311', 'farmaceut', '$2y$12$qX83UfVgBd7NoAiuxEkHnOtloUpaSNvUq3OfFlS8fBetoqI91aRxO', false, 1, true);
insert into user (USER_TYPE, email, name, surname, address, city, country, phone_number, username, password, first_login, rating, enabled) values ('DERMATOLOGIST', 'dermatolog@gmail.com', 'Petar', 'Markuza', 'Telep420', 'NS', 'Srbija', '0606023133', 'dermatolog', '$2y$12$qX83UfVgBd7NoAiuxEkHnOtloUpaSNvUq3OfFlS8fBetoqI91aRxO', false, 5, true);

insert into user (USER_TYPE, email, name, surname, address, city, country, phone_number, username, password, pharmacy_id, enabled, first_login) values ('PHARMACY_ADMIN', 'first@gmail.com', 'Pera', 'Peric', 'asdf', 'NS', 'Srbija', '0601234567', 'pharmacyadmin1', '$2y$12$qX83UfVgBd7NoAiuxEkHnOtloUpaSNvUq3OfFlS8fBetoqI91aRxO', 1, true, false);
insert into user (USER_TYPE, email, name, surname, address, city, country, phone_number, username, password, pharmacy_id, enabled, first_login) values ('PHARMACY_ADMIN', 'second@gmail.com', 'Mika', 'Mikic', 'asdf', 'NS', 'Srbija', '0601234568', 'pharmacyadmin2', '$2y$12$qX83UfVgBd7NoAiuxEkHnOtloUpaSNvUq3OfFlS8fBetoqI91aRxO', 1, true, false);
insert into user (USER_TYPE, email, name, surname, address, city, country, phone_number, username, password, pharmacy_id, enabled, first_login) values ('PHARMACY_ADMIN', 'third@gmail.com', 'Sara', 'Saric', 'asdf', 'NS', 'Srbija', '0601234569', 'pharmacyadmin3', '$2y$12$qX83UfVgBd7NoAiuxEkHnOtloUpaSNvUq3OfFlS8fBetoqI91aRxO', 2, true, false);

insert into user (USER_TYPE, email, name, surname, address, city, country, phone_number, username, password, enabled, first_login) values ('SUPPLIER', 'supp1@gmail.com', 'Marko', 'Jovanovic', 'asdf', 'NS', 'Srbija', '0601234569', 'mare', '$2y$12$qX83UfVgBd7NoAiuxEkHnOtloUpaSNvUq3OfFlS8fBetoqI91aRxO', true, false);
insert into user (USER_TYPE, email, name, surname, address, city, country, phone_number, username, password, enabled, first_login) values ('SUPPLIER', 'supp2@gmail.com', 'Jovan', 'Petrovic', 'asdf', 'NS', 'Srbija', '0601234569', 'jova', '$2y$12$qX83UfVgBd7NoAiuxEkHnOtloUpaSNvUq3OfFlS8fBetoqI91aRxO', true, false);
insert into user (USER_TYPE, email, name, surname, address, city, country, phone_number, username, password, enabled, first_login) values ('SUPPLIER', 'supp3@gmail.com', 'Stefan', 'Teodorovic', 'asdf', 'NS', 'Srbija', '0601234569', 'stefan', '$2y$12$qX83UfVgBd7NoAiuxEkHnOtloUpaSNvUq3OfFlS8fBetoqI91aRxO', true, false);
insert into user (USER_TYPE, email, name, surname, address, city, country, phone_number, username, password, first_login, rating, enabled) values ('PHARMACIST', 'workaholic@gmail.com', 'Mika', 'Markuka', 'Telep', 'NS', 'Srbija', '0620602311', 'workaholic', '$2y$12$qX83UfVgBd7NoAiuxEkHnOtloUpaSNvUq3OfFlS8fBetoqI91aRxO', false, 1, true);

insert into user (USER_TYPE, email, name, surname, address, city, country, phone_number, username, password, enabled, first_login) values ('SYSTEM_ADMIN', 'admin@gmail.com', 'Ranko', 'Urosevic', 'asdf', 'NS', 'Srbija', '0601234569', 'rane', '$2y$12$qX83UfVgBd7NoAiuxEkHnOtloUpaSNvUq3OfFlS8fBetoqI91aRxO', true, false);

insert into role (name) values ('ROLE_PATIENT');
insert into role (name) values ('ROLE_SYSTEM_ADMIN');
insert into role (name) values ('ROLE_PHARMACY_ADMIN');
insert into role (name) values ('ROLE_SUPPLIER');
insert into role (name) values ('ROLE_DERMATOLOGIST');
insert into role (name) values ('ROLE_PHARMACIST');

insert into user_role (user_id, role_id) values (1, 1);
insert into user_role (user_id, role_id) values (2, 1);
insert into user_role (user_id, role_id) values (3, 1);
insert into user_role (user_id, role_id) values (4, 1);
insert into user_role (user_id, role_id) values (5, 6);
insert into user_role (user_id, role_id) values (6, 5);
insert into user_role (user_id, role_id) values (7, 3);
insert into user_role (user_id, role_id) values (8, 3);
insert into user_role (user_id, role_id) values (9, 3);
insert into user_role (user_id, role_id) values (10, 4);
insert into user_role (user_id, role_id) values (11, 4);
insert into user_role (user_id, role_id) values (12, 4);
insert into user_role (user_id, role_id) values (13, 6);
insert into user_role (user_id, role_id) values (14, 2);


insert into employment (EMPLOYMENT_TYPE, end, start, pharmacy_id, pharmacist_id) values ('EMPLOYMENT_PHARMACIST', 20, 9, 1, 5);
insert into employment (EMPLOYMENT_TYPE, end, start, pharmacy_id, dermatologist_id) values ('EMPLOYMENT_DERMATOLOGIST', 20, 8, 1, 6);
insert into employment (EMPLOYMENT_TYPE, end, start, pharmacy_id, dermatologist_id) values ('EMPLOYMENT_DERMATOLOGIST', 19, 9, 2, 6);
insert into employment (EMPLOYMENT_TYPE, end, start, pharmacy_id, pharmacist_id) values ('EMPLOYMENT_PHARMACIST', 24, 0, 1, 13);

insert into appointment (APPOINTMENT_TYPE, start, end, price, patient_id, pharmacist_id, dermatologist_id, pharmacy_id, done) values ('APPOINTMENT_PHARMACIST', '2021-01-22 18:30:00', '2021-01-22 19:30:00', 1000, 1, 5, null, 1, true);
insert into appointment (APPOINTMENT_TYPE, start, end, price, patient_id, pharmacist_id, dermatologist_id, pharmacy_id, done) values ('APPOINTMENT_PHARMACIST', '2021-01-22 19:45:00', '2021-01-22 20:30:00', 1000, 1, 5, null, 1, true);
insert into appointment (APPOINTMENT_TYPE, start, end, price, patient_id, pharmacist_id, dermatologist_id, pharmacy_id, done) values ('APPOINTMENT_PHARMACIST', '2021-01-23 12:30:00', '2021-01-23 17:30:00', 1000, 2, 5, null, 1, true);
insert into appointment (APPOINTMENT_TYPE, start, end, price, patient_id, pharmacist_id, dermatologist_id, pharmacy_id, done) values ('APPOINTMENT_PHARMACIST', '2021-02-22 18:30:00', '2021-02-22 19:30:00', 1000, 3, 5, null, 1, true);

insert into appointment (APPOINTMENT_TYPE, start, end, price, patient_id, pharmacist_id, dermatologist_id, pharmacy_id, done) values ('APPOINTMENT_PHARMACIST', '2021-05-02 18:30:00', '2021-05-02 23:30:00', 1000, 3, 13, null, 1, false);
insert into appointment (APPOINTMENT_TYPE, start, end, price, patient_id, pharmacist_id, dermatologist_id, pharmacy_id, done) values ('APPOINTMENT_DERMATOLOGIST', '2021-05-02 17:30:00', '2021-05-02 18:45:00', 1000, 2, null, 6, 2, false);

insert into appointment (APPOINTMENT_TYPE, start, end, price, patient_id, pharmacist_id, dermatologist_id, pharmacy_id, done) values ('APPOINTMENT_DERMATOLOGIST', '2021-01-22 16:30:00', '2021-01-22 18:30:00', 1000, 1, null, 6, 1, true);
insert into appointment (APPOINTMENT_TYPE, start, end, price, patient_id, pharmacist_id, dermatologist_id, pharmacy_id, done) values ('APPOINTMENT_DERMATOLOGIST', '2021-02-22 9:30:00', '2021-02-22 10:00', 1000, 4, null, 6, 2, true);

insert into appointment (APPOINTMENT_TYPE, start, end, price, patient_id, pharmacist_id, dermatologist_id, pharmacy_id, done) values ('APPOINTMENT_DERMATOLOGIST', '2021-05-22 16:30:00', '2021-05-22 18:30:00', 1000, null, null, 6, 1, false);
insert into appointment (APPOINTMENT_TYPE, start, end, price, patient_id, pharmacist_id, dermatologist_id, pharmacy_id, done) values ('APPOINTMENT_DERMATOLOGIST', '2021-06-22 9:30:00', '2021-06-22 10:00', 1000, null, null, 6, 2, false);

insert into absence (description, start, end, approved, doctor_id) values ("Ocu kuci!", '2021-06-10 16:30:00', '2021-06-10 19:30:00', true, 5);
insert into absence (description, start, end, approved, doctor_id) values ("Ocu kuci!", '2021-06-15 9:30:00', '2021-06-20 19:30:00', false, 5);
insert into absence (description, start, end, approved, doctor_id) values ("Ocu kuci!", '2021-06-15 9:30:00', '2021-06-19 19:30:00', true, 5);
insert into absence (description, start, end, approved, doctor_id) values ("Ocu kuci!", '2021-06-15 9:30:00', '2021-06-20 19:30:00', true, 6);

insert into medicine (medicine_code, name, medicine_type, form, composition, manufacturer, prescription, additional_comments, average_rating)
	values ('s123', 'medicine1', 0, 1, 'Paracetamol 500 mg, Maize starch, potassium sorbate (E 202), purified talc, stearic acid, povidone\nstarch pregelatinised, hypromellose, triacetin', 'GE Healthcare', true, 'Not for every day usage.', 4);
insert into medicine (medicine_code, name, medicine_type, form, composition, manufacturer, prescription, additional_comments, average_rating)
	values ('s124', 'medicine2', 0, 1, 'random stuff', 'mrsisa', false, 'comment', 2);
insert into medicine (medicine_code, name, medicine_type, form, composition, manufacturer, prescription, additional_comments, average_rating)
	values ('S125', 'medicine3', 1, 2, 'random stuff', 'mrsisa', true, null, 2);
insert into medicine (medicine_code, name, medicine_type, form, composition, manufacturer, prescription, additional_comments, average_rating)
	values ('S126', 'bensedin', 0, 2, 'drugs', 'mrsisa', true, 'commentary channel', 3);
	

insert into substitute_medicine (medicine_id, substitute_medicine_id) values (1, 2);
insert into substitute_medicine (medicine_id, substitute_medicine_id) values (1, 3);
insert into substitute_medicine (medicine_id, substitute_medicine_id) values (2, 1);
insert into substitute_medicine (medicine_id, substitute_medicine_id) values (4, 1);
insert into substitute_medicine (medicine_id, substitute_medicine_id) values (4, 2);
insert into substitute_medicine (medicine_id, substitute_medicine_id) values (4, 3);

insert into allergy (medicine_id, patient_id) values (1, 3);
insert into allergy (medicine_id, patient_id) values (2, 2);
insert into allergy (medicine_id, patient_id) values (2, 1);
insert into allergy (medicine_id, patient_id) values (4, 1);

insert into medicine_pharmacy (cost, amount, pharmacy_id, medicine_id) values (500, 0, 1, 1);
insert into medicine_pharmacy (cost, amount, pharmacy_id, medicine_id) values (600, 400, 1, 2);
insert into medicine_pharmacy (cost, amount, pharmacy_id, medicine_id) values (1000, 50, 1, 3);
insert into medicine_pharmacy (cost, amount, pharmacy_id, medicine_id) values (10, 5000, 1, 4);
insert into medicine_pharmacy (cost, amount, pharmacy_id, medicine_id) values (500, 500, 2, 1);
insert into medicine_pharmacy (cost, amount, pharmacy_id, medicine_id) values (800, 800, 2, 2);

insert into medicine_supply (supplier_id, medicine_id, quantity) values (10, 1, 200);
insert into medicine_supply (supplier_id, medicine_id, quantity) values (10, 2, 100); 
insert into medicine_supply (supplier_id, medicine_id, quantity) values (10, 3, 500);
insert into medicine_supply (supplier_id, medicine_id, quantity) values (11, 1, 50);
insert into medicine_supply (supplier_id, medicine_id, quantity) values (11, 2, 0); 
insert into medicine_supply (supplier_id, medicine_id, quantity) values (11, 3, 15);
insert into medicine_supply (supplier_id, medicine_id, quantity) values (12, 1, 150);
insert into medicine_supply (supplier_id, medicine_id, quantity) values (12, 2, 600); 
insert into medicine_supply (supplier_id, medicine_id, quantity) values (12, 3, 100);

insert into purchase_order (order_name, due_date_offer) values ('order1', '2021-07-05');
insert into purchase_order (order_name, due_date_offer) values ('order2', '2021-07-01');
insert into purchase_order (order_name, due_date_offer) values ('order3','2021-07-10');

insert into purchase_order_medicine (purchase_order_id,  medicine_id, quantity) values (1, 1, 20);
insert into purchase_order_medicine (purchase_order_id,  medicine_id, quantity) values (1, 2, 30);
insert into purchase_order_medicine (purchase_order_id,  medicine_id, quantity) values (1, 3, 15);

insert into purchase_order_medicine (purchase_order_id, medicine_id, quantity) values (2, 1, 5);
insert into purchase_order_medicine (purchase_order_id, medicine_id, quantity) values (2, 3, 10);

insert into purchase_order_medicine (purchase_order_id,  medicine_id, quantity) values (3, 2, 20);

insert into purchase_order_supplier (purchase_order_id, supplier_id, delivery_date, price, offer_status) values (1, 10, '2021-06-10', 1000, 0);
insert into purchase_order_supplier (purchase_order_id, supplier_id, delivery_date, price, offer_status) values (2, 10, '2021-07-10', 900, 1);
insert into purchase_order_supplier (purchase_order_id, supplier_id, delivery_date, price, offer_status) values (3, 10, '2021-05-26', 700, 2);

insert into complaints (complaint_type, text, patient_id, dermatologist_id, pharmacist_id, pharmacy_id) values ('COMPLAINT_DERMATOLOGIST', 'Dermatologist has given me the wrong medicine!', 1, 6, null, null);
insert into complaints (complaint_type, text, patient_id, dermatologist_id, pharmacist_id, pharmacy_id) values ('COMPLAINT_DERMATOLOGIST', 'Can you please fire dermatologist Peter? He is very unprofessional!', 1, 6, null, null);