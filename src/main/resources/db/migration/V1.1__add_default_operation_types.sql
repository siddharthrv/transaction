INSERT INTO operation_type
(changed, created, deleted, version, description, is_credit)
VALUES(NULL, now(), 0, 0, 'Normal Purchase', 0);
INSERT INTO operation_type
(changed, created, deleted, version, description, is_credit)
VALUES(NULL, now(), 0, 0, 'Purchase with installments', 0);
INSERT INTO operation_type
(changed, created, deleted, version, description, is_credit)
VALUES(NULL, now(), 0, 0, 'Withdrawal', 0);
INSERT INTO operation_type
(changed, created, deleted, version, description, is_credit)
VALUES(NULL, now(), 0, 0, 'Credit Voucher', 1);