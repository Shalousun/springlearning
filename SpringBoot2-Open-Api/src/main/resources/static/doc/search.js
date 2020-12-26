let api = [];
api.push({
    alias: 'BallController',
    order: '1',
    link: '测试openapi',
    desc: '测试openapi',
    list: []
})
api[0].list.push({
    order: '1',
    desc: '根据id获取',
});
api[0].list.push({
    order: '2',
    desc: '查询所有',
});
api[0].list.push({
    order: '3',
    desc: '',
});
api[0].list.push({
    order: '4',
    desc: '获取枚举参数2',
});
api[0].list.push({
    order: '5',
    desc: 'app测试3',
});
api.push({
    alias: 'DownloadController',
    order: '2',
    link: '文件下载测试',
    desc: '文件下载测试',
    list: []
})
api[1].list.push({
    order: '1',
    desc: '下载普通文件文件',
});
api.push({
    alias: 'FileUploadController',
    order: '3',
    link: '文件上传测试',
    desc: '文件上传测试',
    list: []
})
api[2].list.push({
    order: '1',
    desc: '上传单个文件',
});
api[2].list.push({
    order: '2',
    desc: '批量上传文件',
});
api.push({
    alias: 'error',
    order: '4',
    link: 'error_code_list',
    desc: '错误码列表',
    list: []
})
api.push({
    alias: 'dict',
    order: '5',
    link: 'dict_list',
    desc: '数据字典',
    list: []
})
api[4].list.push({
    order: '1',
    desc: 'http状态码字典',
});
document.onkeydown = keyDownSearch;
function keyDownSearch(e) {
    const theEvent = e;
    const code = theEvent.keyCode || theEvent.which || theEvent.charCode;
    if (code == 13) {
        const search = document.getElementById('search');
        const searchValue = search.value;
        let searchArr = [];
        for (let i = 0; i < api.length; i++) {
            let apiData = api[i];
            const desc = apiData.desc;
            if (desc.indexOf(searchValue) > -1) {
                searchArr.push({
                    order: apiData.order,
                    desc: apiData.desc,
                    link: apiData.link,
                    list: apiData.list
                });
            } else {
                let methodList = apiData.list || [];
                let methodListTemp = [];
                for (let j = 0; j < methodList.length; j++) {
                    const methodData = methodList[j];
                    const methodDesc = methodData.desc;
                    if (methodDesc.indexOf(searchValue) > -1) {
                        methodListTemp.push(methodData);
                        break;
                    }
                }
                if (methodListTemp.length > 0) {
                    const data = {
                        order: apiData.order,
                        desc: apiData.desc,
                        link: apiData.link,
                        list: methodListTemp
                    };
                    searchArr.push(data);
                }
            }
        }
        let html;
        if (searchValue == '') {
            const liClass = "";
            const display = "display: none";
            html = buildAccordion(api,liClass,display);
            document.getElementById('accordion').innerHTML = html;
        } else {
            const liClass = "open";
            const display = "display: block";
            html = buildAccordion(searchArr,liClass,display);
            document.getElementById('accordion').innerHTML = html;
        }
        const Accordion = function (el, multiple) {
            this.el = el || {};
            this.multiple = multiple || false;
            const links = this.el.find('.dd');
            links.on('click', {el: this.el, multiple: this.multiple}, this.dropdown);
        };
        Accordion.prototype.dropdown = function (e) {
            const $el = e.data.el;
            $this = $(this), $next = $this.next();
            $next.slideToggle();
            $this.parent().toggleClass('open');
            if (!e.data.multiple) {
                $el.find('.submenu').not($next).slideUp("20").parent().removeClass('open');
            }
        };
        new Accordion($('#accordion'), false);
    }
}

function buildAccordion(apiData, liClass, display) {
    let html = "";
    let doc;
    if (apiData.length > 0) {
        for (let j = 0; j < apiData.length; j++) {
            html += '<li class="'+liClass+'">';
            html += '<a class="dd" href="#_' + apiData[j].link + '">' + apiData[j].order + '.&nbsp;' + apiData[j].desc + '</a>';
            html += '<ul class="sectlevel2" style="'+display+'">';
            doc = apiData[j].list;
            for (let m = 0; m < doc.length; m++) {
                html += '<li><a href="#_' + apiData[j].order + '_' + doc[m].order + '_' + doc[m].desc + '">' + apiData[j].order + '.' + doc[m].order + '.&nbsp;' + doc[m].desc + '</a> </li>';
            }
            html += '</ul>';
            html += '</li>';
        }
    }
    return html;
}