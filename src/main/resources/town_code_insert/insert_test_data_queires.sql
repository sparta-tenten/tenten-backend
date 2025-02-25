INSERT INTO p_user (user_name, password, email, role, address, detail_address, phone_number,
                    town_code)
VALUES ('customer01', 'Password@123', 'customer01@example.com', 'CUSTOMER', '서울특별시 종로구',
        '청운동 123-4', '010-1234-5678', '1111010100');

INSERT INTO p_user (user_name, password, email, role, address, detail_address, phone_number,
                    town_code)
VALUES ('owner01', 'SecurePass@456', 'owner01@example.com', 'OWNER', '서울특별시 종로구',
        '신교동 567-8',
        '010-9876-5432', '1111010200');

-- 1. 카테고리 데이터 삽입
INSERT INTO p_category (id, name, created_at, updated_at, deleted_at, is_deleted)
VALUES ('550e8400-e29b-41d4-a716-446655440000', '한식', NOW(), NOW(), NULL, FALSE);

INSERT INTO p_category (id, name, created_at, updated_at, deleted_at, is_deleted)
VALUES ('550e8400-e29b-41d4-a716-446655440001', '양식', NOW(), NOW(), NULL, FALSE);

-- 2. 가게 (Store) 데이터 삽입
INSERT INTO p_store (id, name, address, phone_number, image, user_id, category_id, town_code,
                     created_at, updated_at, deleted_at, is_deleted, store_grade,
                     total_review_count)
VALUES ('550e8400-e29b-41d4-a716-446655440010', '김밥천국', '서울특별시 종로구 청운동 1-1', '010-1111-2222',
        'kimbap.jpg',
        1, '550e8400-e29b-41d4-a716-446655440000',
        '1111010100', NOW(), NOW(), NULL, FALSE, 0, 0);

INSERT INTO p_store (id, name, address, phone_number, image, user_id, category_id, town_code,
                     created_at, updated_at, deleted_at, is_deleted, store_grade,
                     total_review_count)
VALUES ('550e8400-e29b-41d4-a716-446655440001', '이탈리안 피자', '서울특별시 종로구 신교동 2-2', '010-2222-3333',
        'pizza.jpg',
        1, '550e8400-e29b-41d4-a716-446655440001',
        '1111010200', NOW(), NOW(), NULL, FALSE, 0, 0);

-- 3. 메뉴 (Menu) 데이터 삽입
INSERT INTO p_menu (id, name, price, description, status, image, store_id, created_by, updated_by,
                    deleted_by, created_at, updated_at, deleted_at)
VALUES ('550e8400-e29b-41d4-a716-446655440100', '참치김밥', 3500, '참치가 들어간 김밥', 'SELLING',
        'tuna_kimbap.jpg',
        '550e8400-e29b-41d4-a716-446655440010', 'admin', 'admin', NULL, NOW(), NOW(), NULL);

INSERT INTO p_menu (id, name, price, description, status, image, store_id, created_by, updated_by,
                    deleted_by, created_at, updated_at, deleted_at)
VALUES ('550e8400-e29b-41d4-a716-446655440101', '마르게리타 피자', 12000, '토마토와 치즈가 조화로운 피자', 'SELLING',
        'margherita.jpg',
        '550e8400-e29b-41d4-a716-446655440001', 'admin', 'admin', NULL, NOW(), NOW(), NULL);

INSERT INTO p_menu_option (id, name, price, menu_id, created_by, updated_by, created_at)
VALUES ('123e4567-e89b-12d3-a456-426614174000', '사이드 메뉴', 3000,
        '550e8400-e29b-41d4-a716-446655440100', 'admin', 'admin', NOW()),
       ('123e4567-e89b-12d3-a456-426614174001', '음료', 2000, '550e8400-e29b-41d4-a716-446655440100',
        'admin', 'admin', NOW()),
       ('123e4567-e89b-12d3-a456-426614174002', '추가 치즈', 500,
        '550e8400-e29b-41d4-a716-446655440101', 'admin', 'admin', NOW()),
       ('123e4567-e89b-12d3-a456-426614174003', '핫소스', 300, '550e8400-e29b-41d4-a716-446655440101',
        'admin', 'admin', NOW());

