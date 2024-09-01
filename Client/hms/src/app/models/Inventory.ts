export class Inventory{
    id:number =0;
    itemName:string = '';
    itemCode:string='';
    quantity:number=0;
    price:number=0;
    expiryDate: Date | undefined = undefined;
    batchNumber:string='';
    location:string='';
    status:string='';
}