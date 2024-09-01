import { Component, OnInit } from '@angular/core';
import { InventoryService } from '../services/inventory.service';
import { FilterObject } from '../models/FilterObject';
import { Inventory } from '../models/Inventory';
import { Router } from '@angular/router';

@Component({
  selector: 'app-inventory',
  templateUrl: './inventory.component.html',
  styleUrls: ['./inventory.component.css']
})
export class InventoryComponent implements OnInit {

  inventoryList : Inventory[] =[];
  displayedColumns: string[] = ['itemName','itemCode','price','quantity','status','actions'];
  filterObject:FilterObject = new FilterObject();
  constructor(
    private inventoryService:InventoryService,
    private router:Router

  ) { }

  ngOnInit(): void {
    this.fetchInventory(this.filterObject);
  }

  fetchInventory(filterObject:FilterObject){
    this.inventoryService.searchInventory(filterObject).subscribe({
      next:(response =>{
        if(response && response.data)
          console.log(response.data['inventory'].dataList);
          this.inventoryList=response.data['inventory'].dataList;
      })
    })
  }

  openAddOrEditDialog(inventoryId:any){
    const url = inventoryId ? `/inventory/edit/${inventoryId}` : '/inventory/edit/new';
    this.router.navigate([url]);
  }

  displayData(id:number){
    this.router.navigate(['/inventory/view/'+id]);
  }

}
