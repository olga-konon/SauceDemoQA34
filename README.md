# Checklist  
#### Preconditions:
- Website: https://www.saucedemo.com/
- Use valid test user: standard_user 
- Use password: secret_sauce

### Login Page
- [ ] Verify that user can log in with valid credentials 
- [ ] Verify that user cannot log in with empty username and it throws an error
- [ ] Verify that user cannot log in with empty password and it throws an error
- [ ] Verify that user cannot log in invalid credentials and it throws an error

### Inventory Page
- [ ] Verify that the user can add one product to the cart
- [ ] Verify that the user can add multiple products to the cart
- [ ] Verify that the user can open the cart page
- [ ] Verify that the user can remove a product from the inventory page

### Cart Page
- [ ] Verify that clicking Item name link opens description page
- [ ] Verify that the user can remove a product from the Cart page
- [ ] Verify that Continue Shopping returns the user to the inventory page
- [ ] Verify that multiple items are added to the cart
- [ ] Verify that Checkout opens the checkout flow

### Checkout
#### Checkout: Your Information
- [ ] Verify that user with empty user data cannot continue and it throws an error
- [ ] Verify that user with valid user data can complete checkout

#### Checkout: Overview
- [ ] Verify that Item total (subtotal) equals to sum of added items
- [ ] Verify that total equals to sum Item total (subtotal) + tax 
- [ ] Verify that Item total (subtotal) equals 0.0, if no items added 

#### Checkout Complete Page
- [ ] Verify that clicking Back home opens inventory Page






