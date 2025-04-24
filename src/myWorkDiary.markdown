
**Day 1:** 

I read the G leavel of the exercise and out from that I decided that I will make 3 pakages.
One package for all the classes related to Customers, one for Pruducts and one for Orders.
Nils our teacher told: maybe good to devide it after simularitys in the classes. 
I do not understand way, but maybe later I will realize and be ohh noo what have I done.

I started with the customers exercises. As all this was new. It was super hard and took me long time and
a lot of research and error search with ai to find my mistakes. If I just had waited one day to our class 
with Bill, I would have got all this explained and done. But I did not know we would actually would do
the exercise that we had on our exam project. Bill (tuesday lecture) we did the customer exercises
after I just finished them with a lot of sweat and tears... (Biggest mistake was typos, in the sql)

### **...customer part finished....**

**Day 2:** 

Started with the Product part of the exercise. Now it was a bit easier as I finally started to 
understand the core code that was needed and new. Now my biggest problem was the SQL errors that 
I am cursed with. The hardest was "Search for a product by a catogory id" I think it took me 2h to solve
the sql errors that keep happening, missing a , or spelling mistakes and other stupid things.

### **...product part finished...**

**Day 3**. 

I started with the first order exercise around 15.00 and finished 23.30, ok I had very long breaks 
feeling a bit unmotivated today. But very happy when I got it to work finally.. the SQL errors was again
my problem. I also had to add new constructor with just the information I wanted to get out (what I had in my sql).
Took me probably 1 hour to understand that after sql error after error... 

I also added a new package for the Orders_products and a klass

**Day 4.**

Today I will start earlier 10.00, Today's goal is to finish 2 more exercises from the Order part.
It started good, I finished the first task in 1 hour (but it was almost the same as the exercises we did before).
now a lunch break :)
Now to the second task, I think slightly more tricky.. lets see.
Ok, it take time, I am so slow writing code... so after 1h I only got the new GETALL method to work.
but I needed it to be able to see if it works to add a new order with many produts... 
Now a I will have a small Break !
Ok now it works to make an order of many products (task 3 in Orders) it was some new things again

NEW stuff: pstms.addBatch() + pstms.executeBatch(); and using a list as a parameter
Today's goals are reached:)

**Day 5.**

"Ange antal av produkten vid orderläggning (Görs tillsammans med ovan INSERT)"
(I thought I did that already when I made the order?)
"Se totala priset på en order (Räkna ihop priset gånger antalet)"

ok, this is going to be a bit more tricky if I can not just do it directly in the loop ... hmm
I could do it directly in the loop. Now it is working wooow that was easy...
but the out print in console does not look very nice right now...

### **...Order Part finished...**

Now I will have to make a head menu and make loops so that you can go between the menus. 
After that I have to make all code look a bit more tidy. Now all my cod is a mess...
I think that will my goal for today...

HeadMenu finished, you can jump between the different menus :)

Now I will go through the Customers part and add some validation and try to make the code more readable.
This will take some time, so many things that need improvement even if it works. 

wooo it is going to take forever, so many things that need to get validated
(not to get error messages or  so that you write in the input in a correct manner)

### **...validations in the customer part finished...**

**Day 6**
Today I just started a bit in the product part, making the menu look nice. Fixed some typos 
will start with adding some validations as well. But there ar so many needed, will start from top 
and work my self down. All validations for SEARCH today and the rest tomorrow... 

**Day 7.**
Still weekend so just a bit of project work today :)
added a new validation in the update price method. first I had to find out way it only worked to update 
with a price 23,50 and not 23.50. hmm by default was it local sv, 
I had to change so that it took both dot and comma. To prevent problems. Now working

I also started to work a bit on how to sign in as a customer. Not finished... but maybe tomorrow :) 

**Day 8.**
So today I am working on the last Validations in the product area. 
I proberbly still missed some, but I did plenty 

upps have no try catch anywhere arrrrrrgg have to putt that in evey where later..

feeling that I pretty ok with validations now, just have a few left on the "order many products" left.
Then I can finally go to the last task on the G level.

Woow I think I am finaly finished with the validations
Tomorrow I will have to add the try- catch every where...


--------------------------------------------------------------------

**All my Validations in Customer section**

* email check must contain a @ (add customer)(update Email)
* let customer check if input is correct before it get added. (add customer)
* password have to be more than 8 characters and minimum one nr (add customer)
* validation check so you just can add a number and not letter for id (update email)
* Validating so that the customer id exist in the database (update email)

----------------------------------------------------------------------

**All my validations in the product part**

* make all letters small, so that when you search you do not have to care about that (search by name)
* validating if the input (name of product) match a name in the database(search by name)
* check if the category exist in the database (search by category)
* can now take both , or . in price (update price)
* number validation, not a letter (update price)
* validate so that the product id exist in database (update price)
* number validation, not a letter (update stock)
* validate so that the product id exist in database (update stock)
* validating that a nr is 0 or over 0 no negative numbers (update stock)
* validate number not string (new product)
* no negative number in the stock Quantity (new product)
* if the product id exist in the database (new product)
* make sure that you can use both , and . when adding price (new product)
* make user able to read all input before sending updating database (new product)
* no negative numbers in the price (update price) (new product)

----------------------------------------------------------------------------------------

**Orders**

* number validation (get history)
* does the customer id exist in database (get history)
* validated that the quantity of the product is not lower than the order quantety (order part 2)
* took away add price manually preventing error in price. (order part2)
* check number validation (order part 2)
* check so that the order id exist(order part 2)
* check if product id exist and write out product info (order part 2)