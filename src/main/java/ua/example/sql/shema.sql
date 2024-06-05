CREATE DATABASE  laptop_registry;

USE laptop_registry;

CREATE TABLE laptops(
		laptop_id INT AUTO_INCREMENT PRIMARY KEY,
		model VARCHAR(20) NOT NULL,
		manufacturer VARCHAR(20) NOT NULL,
		prod_date DATE,
		ram_capacity INT,
		ssd_capacity INT,
		cpu_name VARCHAR(20)
);