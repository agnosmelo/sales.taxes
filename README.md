# sales.taxes
Initial Spring boot app commit. Basic sales tax aplication.

README

Coding Problem: Sales Taxes
Basic sales tax is applicable at a rate of 10% on all goods, except books, food, and medical products that are exempt. Import duty is an additional sales tax applicable on all imported goods at a rate of 5%, with no exemptions. When I purchase items, I receive a receipt which lists the name of all the items and their price (including tax), finishing with the total cost of the items, and the total amounts of sales taxes paid. The rounding rules for sales tax are that for a tax rate of n%, a shelf price of p contains (np/100 rounded up to the nearest 0.05) amount of sales tax. 


1.To start the application you will need: Code file, which you can build using Maven at the URL https://github.com/agnosmelo/sales.taxes and run.

2. Attached to the repository you can find examples of Json files for testing. The name of the file is 'Json Exemple";

3.You can create a request using POSTMAN, after running the application in your IDE. You can choose the POST option and submit the imports suggested in the 'Json Exempla' file.To use this option it is important to choose the 'raw' option and specify that the entry is a JSON in your POSTMAN.
