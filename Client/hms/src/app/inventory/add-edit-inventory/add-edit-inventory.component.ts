import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Inventory } from 'src/app/models/Inventory';
import { InventoryService } from 'src/app/services/inventory.service';

@Component({
  selector: 'app-add-edit-inventory',
  templateUrl: './add-edit-inventory.component.html',
  styleUrls: ['./add-edit-inventory.component.css']
})
export class AddEditInventoryComponent implements OnInit {

  inventory: Inventory = new Inventory();

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private inventoryService: InventoryService
  ) { }

  ngOnInit(): void {
    this.route.paramMap.subscribe(param => {
      const idParam = param.get('id');
      if (idParam) {
        const id = +idParam;
        console.log(id);
        if (!isNaN(id)) {
          this.loadInventory(id);
        }
      }
    });
  }

  loadInventory(id: number): void {
    this.inventoryService.getInventory(id).subscribe({
      next: (response) => {
        console.log(response.data['inventory']);
        if (response && response.status === 200) {
          this.inventory = response.data['inventory'];
        }
      },
      error: (error) => {
        console.error('Error fetching inventory:', error);
      }
    });
  }

  saveInventory(): void {
    this.inventoryService.addOrUpdate(this.inventory).subscribe({
      next:(response)=>{
        if(response && response.status === 200){
          this.router.navigate(['/inventory']);
        }
      }
    })
  }

  goBack(): void {
    this.router.navigate(['/inventory']);
  }
}
