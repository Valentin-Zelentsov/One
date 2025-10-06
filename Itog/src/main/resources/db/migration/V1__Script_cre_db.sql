CREATE TABLE public.customer (
    customer_id SERIAL PRIMARY KEY,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    phone_number VARCHAR(11),
    email VARCHAR(25) 
);

CREATE TABLE public.product (
    product_id SERIAL PRIMARY KEY,
    description VARCHAR(255),
    cost INT NOT NULL,
    quantity INT NOT NULL,
    category VARCHAR(50)
);

CREATE TABLE public.orders (
    order_id SERIAL PRIMARY KEY,
    product_id INT NOT NULL,
    customer_id INT NOT NULL,
    order_date DATE,
    quantity INT NOT NULL,
    status smallint NOT NULL,
    FOREIGN KEY (product_id) REFERENCES product(product_id),
    FOREIGN KEY (customer_id) REFERENCES customer(customer_id)
);

CREATE TABLE public.status (
    status_id SERIAL PRIMARY KEY,
    name_status VARCHAR(50) NOT NULL
);

