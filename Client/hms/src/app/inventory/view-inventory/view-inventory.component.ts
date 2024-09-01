import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Inventory } from 'src/app/models/Inventory';
import { InventoryService } from 'src/app/services/inventory.service';

@Component({
  selector: 'app-view-inventory',
  templateUrl: './view-inventory.component.html',
  styleUrls: ['./view-inventory.component.css']
})
export class ViewInventoryComponent implements OnInit {

  inventory:Inventory = new Inventory();
  constructor(
    private roout:ActivatedRoute,
    private router:Router,
    private inventoryService:InventoryService
  ) { }

  ngOnInit(): void {
    this.roout.paramMap.subscribe(param=>{
      const idParam = param.get('id');
      if(idParam){
        const id = +idParam;
        if(!isNaN(id)){
          this.loadInventory(id);
        }
      }
    })
  }
  loadInventory(id:number){
    this.inventoryService.getInventory(id).subscribe({
      next:(response)=>{
        if(response && response.data){
            this.inventory=response.data['inventory'];
        }
      }
    })
  }
  goBack(){
    this.router.navigate(['/inventory'])
  }

}
