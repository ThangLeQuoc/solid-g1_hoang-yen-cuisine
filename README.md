# 🍲 Hoang Yen Cuisine 🍲

## Features
- View today's menu 📜
- Place lunch order 🍜
- Receive notification of new lunch dishes 💌
- Pay lunch 💵
- For Yen **only** 👧
  - generate report of all orders that have been made for today 📊
  - add new dish to menu ➕🍗

## Limitation
 - No persistent database, only memory 💢
 - No authentication mechanism, hard-code username 💢
 - No GUI, command line only 💢

## How to use
As an ordinary user
The happy workflow:

1.login with your name
2.view menu
3.make order(s)
4.pay them

```
>>>   Welcome to Hoang Yen Cuisine   <<<
usage: hoang_yen_cuisine
 -addDish              Add new dish for today. Usage:
                       -addDish [{"name":"ca","price":25},{"name":"dau-hu","price": 25}]
 -help                 Prints out usage
 -menu                 View menu
 -order <ID of dish>   Orders a lunch
 -pay                  Pays your order within this week
 -poweroverwhelming    God mode
 -quit                 Burns it down
 -report               Generate reports
 -user <username>      Sets username
Enter your command: -menu
>>> MENU for today <<<
Dish 1: Pork, price: 30
Dish 2: Beef, price: 40
Dish 3: Chicken, price: 25
Dish 4: Prawns, price: 40
Dish 5: Salmon, price: 60
Dish 6: Rice, price: 5
Dish 7: Pizza, price: 110
Enter your command: -user phuc
Welcome phuc!
Enter your command: -order 7
You have made an order: Order [id=1, username=phuc, dish=Dish 7: Pizza, price: 110]
Enter your command: -order 2
You have made an order: Order [id=2, username=phuc, dish=Dish 2: Beef, price: 40]
Enter your command: -pay
My lunch orders:
    #1: Pizza - 110
    #2: Beef - 40
----------------
Your bill is 150k VND

You have two payment method:      1. Card      2. Cash 
Which one you choose? 

2
Need not to enter card number
Please Enter Your Address:
desk 420
SUCCESS
Enter your command: -quit
Have a nice day!
```

As Yen (superuser)
Activate this mode with option poweroverwhelming to unlocks additional features: generates report, add new dish(es) to menu.
For better user experience, please follow the following scenario:

1.log in as normal user (phuc, tan)
2.make orders (in order to populate datatabse)
3.activate god mode
4.generate reports
5.add dish
6.verify new dish is added by viewing menu

```

>>>   Welcome to Hoang Yen Cuisine   <<<
usage: hoang_yen_cuisine
 -addDish              Add new dish for today. Usage:
                       -addDish [{"name":"ca","price":25},{"name":"dau-hu","price": 25}]
 -help                 Prints out usage
 -menu                 View menu
 -order <ID of dish>   Orders a lunch
 -pay                  Pays your order within this week
 -poweroverwhelming    God mode
 -quit                 Burns it down
 -report               Generate reports
 -user <username>      Sets username
Enter your command: -user phuc
Welcome phuc!
Enter your command: -order 2
You have made an order: Order [id=1, username=phuc, dish=Dish 2: Beef, price: 40]
Enter your command: -order 3
You have made an order: Order [id=2, username=phuc, dish=Dish 3: Chicken, price: 25]
Enter your command: -user tan
Welcome tan!
Enter your command: -order 1
You have made an order: Order [id=3, username=tan, dish=Dish 1: Pork, price: 30]
Enter your command: -order 4
You have made an order: Order [id=4, username=tan, dish=Dish 4: Prawns, price: 40]
Enter your command: -poweroverwhelming
All your base are belong to us
Enter your command: -report
tan
     Order 3:  Pork 30
     Order 4:  Prawns 40
Total: 70
----------------------------
phuc
     Order 1:  Beef 40
     Order 2:  Chicken 25
Total: 65
----------------------------

Enter your command: -addDish [{"name":"Crab","price":80}]
Enter your command: -menu
>>> MENU for today <<<
Dish 1: Pork, price: 30
Dish 2: Beef, price: 40
Dish 3: Chicken, price: 25
Dish 4: Prawns, price: 40
Dish 5: Salmon, price: 60
Dish 6: Rice, price: 5
Dish 7: Pizza, price: 110
Dish 8: Crab, price: 80
Enter your command: -quit
Have a nice day!

```
