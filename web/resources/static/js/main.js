/**
 * Created by tranv on 11/29/2016.
 */
//Check font
/*if(!doesFontExist("UTM-Neo-Sans-Intel")) {
    addFont("UTM-Neo-Sans-Intel", "./static/font/UTM-Neo-Sans-Intel.ttf");
}
 */
function addFont(fontName,fontURL){
    var newStyle = document.createElement('style');
    newStyle.appendChild(document.createTextNode("\
	@font-face {\
	    font-family: '" + fontName + "';\
	    src: url('" + fontURL + "') format('truetype');\
	}\
	"));
    var head = document.getElementsByTagName("head")[0];
    head.appendChild(newStyle);
}


function doesFontExist(fontName) {
    try{
        // creating our in-memory Canvas element where the magic happens
        var canvas = document.createElement("canvas");
        var context = canvas.getContext("2d");

        // the text whose final pixel size I want to measure
        var text = "abcdefghijklmnopqrstuvwxyz0123456789";

        // specifying the baseline font
        context.font = "72px monospace";

        // checking the size of the baseline text
        var baselineSize = context.measureText(text).width;

        // specifying the font whose existence we want to check
        context.font = "72px '" + fontName + "', monospace";

        // checking the size of the font we want to check
        var newSize = context.measureText(text).width;

        // removing the Canvas element we created
        delete canvas;

        //
        // If the size of the two text instances is the same, the font does not exist because it is being rendered
        // using the default sans-serif font
        //
        if (newSize == baselineSize) {
            return false;
        } else {
            return true;
        }
    }catch (e) {
        return true;
    }
}

function convertIntToDateTime(timestampStr) {
    console.log('convertIntToDateTime ====== ', timestampStr);
    if(!Number(timestampStr)) return '';
    var timestamp = Number(timestampStr);
    var date = new Date(timestamp);

    var day = date.getDate();
    var month = date.getMonth() + 1; // Lưu ý: Tháng trong đối tượng Date bắt đầu từ 0
    var year = date.getFullYear();

    var hours = date.getHours();
    var minutes = date.getMinutes();
    var seconds = date.getSeconds();

    // Đảm bảo định dạng dd/mm/yyyy hh:MM:ss
    var formattedDate = ('0' + day).slice(-2) + '/' + ('0' + month).slice(-2) + '/' + year;
    var formattedTime = ('0' + hours).slice(-2) + ':' + ('0' + minutes).slice(-2) + ':' + ('0' + seconds).slice(-2);
    var formattedDateTime = formattedDate + ' ' + formattedTime;

    return formattedDateTime;
}

function resetSelectOption(idTag) {
    $("#"+idTag).val("");
    $("#"+idTag+" option").prop("selected", false); // Hủy chọn tất cả các option
    $("#"+idTag+" option:first").prop("selected", true); // Chọn option mặc định (ví dụ: option đầu tiên)
    $("#"+idTag).trigger("change"); // Cập nhật giao diện
}

//Kiểm tra định dạng dd/mm/yyyy
function isValidDateFormat(dateString) {
    // Kiểm tra định dạng dd/mm/yyyy
    var pattern = /^\d{2}\/\d{2}\/\d{4}$/;

    if (!pattern.test(dateString)) {
        return false; // Không khớp với định dạng
    }

    // Kiểm tra tính hợp lệ của ngày/tháng/năm
    var parts = dateString.split('/');
    var day = parseInt(parts[0], 10);
    var month = parseInt(parts[1], 10);
    var year = parseInt(parts[2], 10);

    if (isNaN(day) || isNaN(month) || isNaN(year)) {
        return false; // Ngày/tháng/năm không hợp lệ
    }

    if (day < 1 || day > 31 || month < 1 || month > 12) {
        return false; // Ngày/tháng không nằm trong khoảng hợp lệ
    }

    // Kiểm tra năm nhuận (nếu cần)
    if (month === 2) {
        if ((year % 4 === 0 && year % 100 !== 0) || (year % 400 === 0)) {
            if (day > 29) {
                return false; // Năm nhuận tháng 2 không vượt quá 29 ngày
            }
        } else {
            if (day > 28) {
                return false; // Tháng 2 không nhuận không vượt quá 28 ngày
            }
        }
    }

    // Trường hợp khác, trả về true
    return true;
}

var digitsOnly = /[1234567890]/g;
var forDate=/[1234567890/]/g;

function restrictCharacters(myfield, e, restrictionType) {
    if (!e) var e = window.event
    if (e.keyCode) code = e.keyCode;
    else if (e.which) code = e.which;
    var character = String.fromCharCode(code);

    // if they pressed esc... remove focus from field...
    if (code==27) { this.blur(); return false; }

    // ignore if they are press other keys
    // strange because code: 39 is the down key AND ' key...
    // and DEL also equals .
    if (!e.ctrlKey && code!=9 && code!=8 && code!=36 && code!=37 && code!=38 && (code!=39 || (code==39 && character=="'")) && code!=40) {
        if (character.match(restrictionType)) {
            return true;
        } else {
            return false;
        }

    }
}

function formatNumber(value) {
    // Xóa tất cả các ký tự không phải số
    value = value.replace(/\D/g, '');

    // Định dạng lại số với dấu '.' sau mỗi 3 chữ số
    value = value.replace(/\B(?=(\d{3})+(?!\d))/g, '.');

    // Trả về giá trị đã định dạng lại
    return value;
}

function getNumberFromText(value) {
    if(!value) return null;
    // Xóa tất cả các ký tự không phải số
    return Number(value.replace(/\D/g, ''));
}

function formatNumber1(input) {
    // Xóa dấu '.' trong giá trị nhập vào
    var value = String(input).replace(/\./g, '');

    // Định dạng lại số với dấu '.' sau mỗi 3 chữ số
    value = value.replace(/\B(?=(\d{3})+(?!\d))/g, '.');

    // value = value.replace(',', '.');

    // Gán giá trị đã định dạng lại vào ô input
    input.value = value;
}

function formatNumberReturn(number) {
    if(!number) return '';
    // Chuyển đổi số thành chuỗi và xóa dấu '.'
    var value = String(number).replace(/\./g, '');

    // Định dạng lại số với dấu '.' sau mỗi 3 chữ số
    value = value.replace(/\B(?=(\d{3})+(?!\d))/g, '.');

    // Trả về giá trị đã định dạng lại
    return value;
}

//param format dd/mm/yyyy
function getDateObjectFromDateStr(dateStr) {
    var parts = dateStr.split('/');
    return new Date(parts[2], parts[1] - 1, parts[0]);
}

function getNotificationTotal() {
    $.ajax({
        type: "GET",
        url: ospApiUrl + "/notifications/count?view_status=0",
        dataType: 'json',
        contentType: 'application/json; charset=utf-8',
        success: function (response) {
            notification.totalNoSeen = response;
            $("#notification-count").html(notification.totalNoSeen);
        },
        error: function (e) {
            console.log("ERROR: ", e);
        },
        done: function (e) {
            console.log("DONE");
        }
    })
}

function getNotifications() {
    notification.page = 1;
    $.ajax({
        type: "GET",
        url: ospApiUrl + "/notifications/page",
        dataType: 'json',
        contentType: 'application/json; charset=utf-8',
        success: function (response) {
            var page = response;
            notification.total = page.rowCount;
            var items = page.items;
            notification.items = items;

            var listContainer = document.getElementById('notification-items');
            // Xóa các phần tử con hiện tại của notificationListContainer
            while (listContainer.firstChild) {
                listContainer.firstChild.remove();
            }
            var ulElement = document.createElement('ul');
            ulElement.classList.add('notification-list');
            listContainer.appendChild(ulElement);
            listContainer.style.display = 'block';


            // Đổ dữ liệu vào danh sách
            items.forEach(function(item) {
                var li = createNotificationItem(item);
                listContainer.appendChild(li);
            });
            if(items.length < 20) $("#load-more-button").hide();
        },
        error: function (e) {
            console.log("ERROR: ", e);
        },
        done: function (e) {
            console.log("DONE");
        }
    })
}

function loadMoreNotifications() {
    notification.page += 1;
    $.ajax({
        type: "GET",
        url: ospApiUrl + "/notifications/page?page="+notification.page,
        dataType: 'json',
        contentType: 'application/json; charset=utf-8',
        success: function (response) {
            var page = response;
            notification.total = page.rowCount;
            var items = page.items;
            notification.items.push(items);

            var listContainer = document.getElementById('notification-items');


            // Đổ dữ liệu vào danh sách
            items.forEach(function(item) {
                var li = createNotificationItem(item);
                listContainer.appendChild(li);
            });
            if(items.length < 20) $("#load-more-button").hide();
        },
        error: function (e) {
            console.log("ERROR: ", e);
        },
        done: function (e) {
            console.log("DONE");
        }
    })
}

function clickOnNotification(notifyItem) {
    if(notifyItem.view_status === 0) {
        actionUpdateNotificationToSeen(notifyItem);
        $("#notification-item-"+notifyItem.id).find(".status").html("Đã xem");
    }


    var currentURL = window.location.href; // URL hiện tại

    var origin = window.location.origin;
    var currentDomain = window.location.hostname; // Domain hiện tại

    var currentPort = window.location.port; // Port hiện tại

    var currentPathname = window.location.pathname; // Pathname hiện tại

    var currentContextPath = currentPathname.split('/')[1]; // Context path hiện tại

    console.log("URL:", currentURL);
    console.log("Domain:", currentDomain);
    console.log("Port:", currentPort);
    console.log("Context Path:", currentContextPath);
    var myUrl = origin + "/" + currentContextPath;
    if(notifyItem.target_url) {
        var currentDomain = window.location.hostname;
        console.log(currentDomain);
        var newTab = window.open(myUrl + notifyItem.target_url, '_blank');
        newTab.focus();
    }

}

function actionUpdateNotificationToSeen(notifyItem) {
    var url = ospApiUrl + "/notifications/view-status";
    var data = { id: notifyItem.id, view_status: 1 };

    $.ajax({
        url: url,
        type: 'PUT',
        contentType: 'application/json',
        data: JSON.stringify(data),
        success: function(response) {
            // Xử lý khi yêu cầu thành công
            console.log('Yêu cầu thành công');
            notification.totalNoSeen -= 1;
            $("#notification-count").html(notification.totalNoSeen);

        },
        error: function(xhr, status, error) {
            // Xử lý khi yêu cầu thất bại
            console.log('Yêu cầu thất bại');
        }
    });
}

function createNotificationItem(item) {
    var liElement = document.createElement('li');
    liElement.classList.add('notification-item');
    liElement.onclick = function() {
        clickOnNotification(item)
    };
    liElement.id = "notification-item-"+item.id;

    var titleElement = document.createElement('div');
    titleElement.classList.add('title');
    titleElement.textContent = item.content;
    liElement.appendChild(titleElement);

    var detailsElement = document.createElement('div');
    detailsElement.classList.add('details');

    var timeElement = document.createElement('div');
    timeElement.classList.add('time');
    timeElement.textContent = convertIntToDateTime(item.entry_date_time);
    detailsElement.appendChild(timeElement);

    var statusElement = document.createElement('div');
    statusElement.classList.add('status');
    statusElement.textContent = item.view_status === 0? 'Chưa xem' : 'Đã xem';
    detailsElement.appendChild(statusElement);

    liElement.appendChild(detailsElement);

    return liElement;
}
