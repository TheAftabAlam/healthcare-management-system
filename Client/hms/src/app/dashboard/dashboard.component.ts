import { Component, OnInit } from '@angular/core';
import * as Highcharts from 'highcharts';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {
  Highcharts: typeof Highcharts = Highcharts;
  chartOptionsPatientsOverTime: Highcharts.Options = {
    title: {
      text: 'Patients Over Time'
    },
    xAxis: {
      categories: ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun']
    },
    yAxis: {
      title: {
        text: 'Number of Patients'
      }
    },
    series: [{
      name: 'Patients',
      type: 'line', // or 'column'
      data: [10, 20, 30, 40, 50, 60]
    }]
  };

  chartOptionsDoctorsOverTime: Highcharts.Options = {
    title: {
      text: 'Doctors Over Time'
    },
    xAxis: {
      categories: ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun']
    },
    yAxis: {
      title: {
        text: 'Number of Doctors'
      }
    },
    series: [{
      name: 'Doctors',
      type: 'line', // or 'column'
      data: [5, 10, 15, 20, 25, 30]
    }]
  };

  chartOptionsDoctors: Highcharts.Options = {
    title: {
      text: 'Top 5 Doctors'
    },
    xAxis: {
      type: 'category'
    },
    yAxis: {
      title: {
        text: 'Number of Appointments'
      }
    },
    series: [{
      type: 'bar',
      name: 'Doctors',
      data: [
        ['Dr. Smith', 10],
        ['Dr. Jones', 8],
        ['Dr. Taylor', 6],
        ['Dr. Brown', 5],
        ['Dr. Johnson', 4]
      ]
    }]
  };

  chartOptionsPatients: Highcharts.Options = {
    title: {
      text: 'Emergency Patients'
    },
    series: [{
      type: 'pie',
      name: 'Emergency Cases',
      data: [
        ['Critical', 50],
        ['High', 30],
        ['Medium', 15],
        ['Low', 5]
      ]
    }]
  };

  totalPatients: number = 120;
  totalDoctors: number = 45;

  ngOnInit(): void {
    // Initialization logic here
  }
}
