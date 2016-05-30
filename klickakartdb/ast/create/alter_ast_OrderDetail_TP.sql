

ALTER TABLE `ast_OrderDetail_TP` ADD CONSTRAINT FOREIGN KEY (`itemId`) REFERENCES `ast_Item_M`(`itemId`);



ALTER TABLE `ast_OrderDetail_TP` ADD CONSTRAINT FOREIGN KEY (`orderId`) REFERENCES `ast_OrderMain_T`(`orderId`);

