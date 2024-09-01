import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { User } from 'src/app/models/user.model';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-view',
  templateUrl: './view.component.html',
  styleUrls: ['./view.component.css']
})
export class ViewComponent implements OnInit {

  user: User | null = null; // Initialize to null

  constructor(
    private route: ActivatedRoute,
    private userService: UserService,
    private router: Router
  ) {}

  ngOnInit(): void {
    const id = this.route.snapshot?.paramMap.get('id');
    
    if (id !== null) {
      const userId = +id;
      this.getUser(userId);
    } else {
      console.error('User ID is null');
    }
  }
  

  getUser(id: number): void {
    this.userService.getUser(id).subscribe(
      (response) => {
        this.user = response.data?.user ?? null; // Safely access user
      },
      (error) => {
        console.error('Error fetching user data:', error);
      }
    );
  }

  deleteUser(): void {
    if (confirm('Are you sure you want to delete this user?')) {
      // this.userService.deleteUser(this.user.id).subscribe(() => {
      //   this.router.navigate(['/users']);
      // });
    }
  }

  goBack(): void {
    this.router.navigate(['/user']);
  }
}
