CREATE TABLE roles (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL UNIQUE,
    created_on DATETIME  NOT NULL,
    created_by VARCHAR(100) NOT NULL
);

CREATE TABLE privileges (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL UNIQUE,
	created_on DATETIME  NOT NULL,
    created_by VARCHAR(100) NOT NULL
);

CREATE TABLE roles_privileges (
    role_id BIGINT NOT NULL,
    privilege_id BIGINT NOT NULL,
    PRIMARY KEY (role_id, privilege_id),
    CONSTRAINT fk_rp_role FOREIGN KEY (role_id) REFERENCES roles(id),
    CONSTRAINT fk_rp_privilege FOREIGN KEY (privilege_id) REFERENCES privileges(id)
);


CREATE TABLE users (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    role_id BIGINT,
    created_on DATETIME  NOT NULL,
    created_by VARCHAR(100) NOT NULL,
    updated_on DATETIME  NOT NULL,
    updated_by VARCHAR(100) NOT NULL,
    CONSTRAINT fk_user_role FOREIGN KEY (role_id) REFERENCES roles(id)
);

CREATE TABLE auth_token (
	id BIGINT NOT NULL AUTO_INCREMENT,
    token VARCHAR(255) NOT NULL,
    user_id BIGINT,
    expires_at DATETIME,
    created_on DATETIME  NOT NULL,
    created_by VARCHAR(100) NOT NULL,
    updated_on DATETIME  NOT NULL,
    updated_by VARCHAR(100) NOT NULL,
    PRIMARY KEY (id),
    UNIQUE KEY unique_token (token),
    CONSTRAINT fk_user_auth_token FOREIGN KEY (user_id) REFERENCES users(id)
);

CREATE TABLE users (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    role_id BIGINT,
    created_on DATETIME  NOT NULL,
    created_by VARCHAR(100) NOT NULL,
    updated_on DATETIME  NOT NULL,
    updated_by VARCHAR(100) NOT NULL,
    CONSTRAINT fk_user_role FOREIGN KEY (role_id) REFERENCES roles(id)
);

CREATE TABLE address (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    line1 VARCHAR(255) NOT NULL,
	line2 VARCHAR(255) NULL,
    city VARCHAR(255) NOT NULL,
	state VARCHAR(255) NOT NULL,
    country VARCHAR(255) NOT NULL,
    created_on DATETIME  NOT NULL,
    created_by VARCHAR(100) NOT NULL,
    updated_on DATETIME  NOT NULL,
    updated_by VARCHAR(100) NOT NULL
);

CREATE TABLE animal (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
	alias_name VARCHAR(255) NULL,
	category ENUM('MAMMALS', 'BIRDS', 'REPTILES', 'AMPHIBIANS', 'FISH', 'INSECTS', 'ARACHNIDS') NOT NULL,
    skills VARCHAR(255) NULL,
    created_on DATETIME  NOT NULL,
    created_by VARCHAR(100) NOT NULL,
    updated_on DATETIME  NOT NULL,
    updated_by VARCHAR(100) NOT NULL
);

CREATE TABLE zoo (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL UNIQUE,
    primary_attraction VARCHAR(255) NOT NULL,
    address_id BIGINT NOT NULL,
    created_on DATETIME  NOT NULL,
    created_by VARCHAR(100) NOT NULL,
    updated_on DATETIME  NOT NULL,
    updated_by VARCHAR(100) NOT NULL,
    CONSTRAINT fk_zoo_address FOREIGN KEY (address_id) REFERENCES address(id)
);

CREATE TABLE zoo_animal_map (
    zoo_id BIGINT NOT NULL,
    animal_id BIGINT NOT NULL,
    PRIMARY KEY (zoo_id, animal_id),
    CONSTRAINT fk_zam_zoo FOREIGN KEY (zoo_id) REFERENCES zoo(id),
    CONSTRAINT fk_zam_animal FOREIGN KEY (animal_id) REFERENCES animal(id)
);