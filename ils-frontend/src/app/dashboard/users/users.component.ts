import {Component, ViewChild, ElementRef} from '@angular/core';
import {DataSource} from '@angular/cdk/collections';
import {MdSort} from '@angular/material';
import {BehaviorSubject} from 'rxjs/BehaviorSubject';
import {Observable} from 'rxjs/Observable';
import 'rxjs/add/operator/startWith';
import 'rxjs/add/observable/merge';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/debounceTime';
import 'rxjs/add/operator/distinctUntilChanged';
import 'rxjs/add/observable/fromEvent';
import { ApplicationService } from '../../application.service';

@Component({
  selector: 'app-users-cmp',
  styleUrls: ['users.component.css'],
  templateUrl: 'users.component.html',
  providers: [ApplicationService]
})
export class UsersComponent {
  displayedColumns = ['userId', 'userName', 'email', 'operation'];
  userServiceHelper = new UserServiceHelper(this.appService);
  dataSource: UserDataSource | null;

  constructor(private appService: ApplicationService){
    
  }

  @ViewChild(MdSort) sort: MdSort;
  @ViewChild('filter') filter: ElementRef;

  ngOnInit() {
      this.dataSource = new UserDataSource(this.userServiceHelper, this.sort, this.filter);
      Observable.fromEvent(this.filter.nativeElement, 'keyup')
      .debounceTime(150)
      .distinctUntilChanged()
      .subscribe(() => {
        if (!this.dataSource) { return; }
        this.dataSource.filter = this.filter.nativeElement.value;
      });
  }

  handleCellClick(cell): void {
    console.log(cell);
  }
}

/** An example database that the data source uses to retrieve data for the table. */
export class UserServiceHelper {
  /** Stream that emits whenever the data has been modified. */
  dataChange: BehaviorSubject<Object[]> = new BehaviorSubject<Object[]>([]);
  get data(): Object[] { return this.dataChange.value; }

  constructor(private appService: ApplicationService){
    this.appService.getUsers()
    .subscribe(
      (userList) => {
        this.dataChange.next(userList);
      }
    );
    
  }
}

/**
 * Data source to provide what data should be rendered in the table. Note that the data source
 * can retrieve its data in any way. In this case, the data source is provided a reference
 * to a common data base, UserServiceHelper. It is not the data source's responsibility to manage
 * the underlying data. Instead, it only needs to take the data and send the table exactly what
 * should be rendered.
 */
export class UserDataSource extends DataSource<any> {
  _filterChange = new BehaviorSubject('');
  get filter(): string { return this._filterChange.value; }
  set filter(filter: string) { this._filterChange.next(filter); }

  constructor(private _UserServiceHelper: UserServiceHelper, private _sort: MdSort, private _filter: ElementRef) {
    super();
  }

  /** Connect function called by the table to retrieve one stream containing the data to render. */
  connect(): Observable<Object[]> {
    const displayDataChanges = [
      this._UserServiceHelper.dataChange,
      this._sort.mdSortChange,
      this._filterChange
    ];

    return Observable.merge(...displayDataChanges).map(() => {
      let self = this;
      const filteredData = this._UserServiceHelper.data.slice().filter((item: Object) => {
        let searchStr = (item['name'] + item['email']).toLowerCase();
        return searchStr.indexOf(self.filter.toLowerCase()) != -1;
      });
      return this.getSortedData(filteredData);
    });
  }

  disconnect() {}

  /** Returns a sorted copy of the database data. */
  getSortedData(dataToSort:Object[]): Object[] {
    let data:Object[] = null;
    if(dataToSort == undefined ||dataToSort == null ){
      data = dataToSort;
    }else if(dataToSort.length == 0){
      data = dataToSort;
    }else {
      data = this._UserServiceHelper.data.slice();
    }
    
    if (!this._sort.active || this._sort.direction == '') { return data; }

    return data.sort((a, b) => {
      let propertyA: number|string = '';
      let propertyB: number|string = '';

      switch (this._sort.active) {
        case 'userId': [propertyA, propertyB] = [a['id'], b['id']]; break;
        case 'userName': [propertyA, propertyB] = [a['name'], b['name']]; break;
        case 'email': [propertyA, propertyB] = [a['email'], b['email']]; break;
        case 'color': [propertyA, propertyB] = [a['color'], b['color']]; break;
      }

      let valueA = isNaN(+propertyA) ? propertyA : +propertyA;
      let valueB = isNaN(+propertyB) ? propertyB : +propertyB;

      return (valueA < valueB ? -1 : 1) * (this._sort.direction == 'asc' ? 1 : -1);
    });
  }
}


/**  Copyright 2017 Google Inc. All Rights Reserved.
    Use of this source code is governed by an MIT-style license that
    can be found in the LICENSE file at http://angular.io/license */