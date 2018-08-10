import {Component, OnInit, TemplateRef} from '@angular/core';
import { NzNotificationService } from "ng-zorro-antd";

@Component({
  selector: 'app-notification',
  templateUrl: './notification.component.html',
  styleUrls: ['./notification.component.css']
})
export class NotificationComponent implements OnInit {
  placement = 'topLeft';

  clearBeforeNotifications(): void {
    this.notification.remove();
  }

  createBasicNotification(): void {
    this.notification.config({
      nzPlacement: this.placement
    });
    this.notification.blank('Notification Title', 'This is the content of the notification. This is the content of the notification. This is the content of the notification.');
  }
  createBasicNotificationT(template: TemplateRef<{}>): void {
    this.notification.config({
      nzPlacement: this.placement
    });
    this.notification.template(template,{});
  }

  constructor(private notification: NzNotificationService) {
  }

  ngOnInit() {
  }

}
