
SELECT * FROM orders WHERE customer_id = 1;

SELECT customer.first_name, customer.last_name, COUNT(*) AS total_orders 
FROM customer 
JOIN orders ON customer.customer_id = orders.customer_id 
GROUP BY customer.customer_id 
ORDER BY total_orders DESC; 

SELECT o.order_id, p.description AS product_description, o.quantity, p.cost*o.quantity AS total_cost, c.first_name || ' ' || c.last_name AS full_name 
FROM orders o 
JOIN product p ON o.product_id = p.product_id 
JOIN customer c ON o.customer_id = c.customer_id 
WHERE o.order_id = 1;

SELECT p.category, AVG(o.quantity) AS avg_quantity_per_order 
FROM product p 
JOIN orders o ON p.product_id = o.product_id 
GROUP BY p.category;

SELECT p.category, SUM(p.cost * o.quantity) AS total_sales 
FROM product p 
JOIN orders o ON p.product_id = o.product_id 
GROUP BY p.category 
ORDER BY total_sales DESC;

UPDATE customer SET phone_number='89991234567' WHERE customer_id=1;

UPDATE orders SET status=2 WHERE order_id=1;

UPDATE product SET cost=cost+100 WHERE product_id=1;

DELETE FROM orders WHERE customer_id=1;

DELETE FROM product WHERE product_id=1;