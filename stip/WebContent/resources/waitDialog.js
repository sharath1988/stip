/**
 * 
 */
 var waitDialogShown = false;
 var useWaitDialogTimer = true;
 var waitDialogTimeout = 500;
 var waitDialogTimer;

 function showWaitDialog() {
     if (!waitDialogShown) {
         Richfaces.showModalPanel('waitDialog1');
         waitDialogShown = true;
     }
 }

 function onRequestStart() {
     if (useWaitDialogTimer) {
         waitDialogTimer = setTimeout("showWaitDialog();",waitDialogTimeout);
         } else {
         showWaitDialog();
     }
 }

 function onRequestEnd() {
     if (waitDialogShown) {
         Richfaces.hideModalPanel('waitDialog1');
         waitDialogShown = false;
         } else if (useWaitDialogTimer && waitDialogTimer) {
         clearTimeout(waitDialogTimer);
     }
 }
