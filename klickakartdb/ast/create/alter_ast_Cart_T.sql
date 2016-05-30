

ALTER TABLE `ast_Cart_T` ADD CONSTRAINT FOREIGN KEY (`itemID`) REFERENCES `ast_Item_M`(`itemId`);



ALTER TABLE `ast_Cart_T` ADD CONSTRAINT FOREIGN KEY (`userId`) REFERENCES `ast_User_T`(`userId`);

