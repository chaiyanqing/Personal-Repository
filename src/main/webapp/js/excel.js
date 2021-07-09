	/**
	 * data: 数据
	 * columns: 对应列[{name: data中的key值(必填), displayName: 显示列名称(选填), filter: 过滤器(选填)}]
	 * fileName: 文件名称
	 * title: 文件抬头名称(第一行内容,为空时excel顶格显示)
	 */
	 var exportSheetJS = function(data, columns, fileName, title) {
		//var columns = uiGridExporterService.getColumnHeaders(gridApi.grid, 'all');
		//var data = uiGridExporterService.getData(gridApi.grid, 'all', 'all');
		//var columns = [{align: "right", displayName: "名称", name: "name2", width: "333px"},{align: "left", displayName: "dddd", name: "d", width: 9997},{align: "left", displayName: "c", name: "c", width: 227},];
		//var data = [[{"value": '1'},{value:'2'},{value:'3'}],[{value'1'},{value:'2'},{value:'2'}],[{value'1'},{value:'2'},{value:'2'}],[{value'1'},{value:'2'},{value:'2'}]];
		//var data = [[{"value":"Ethel Price"},{"value":"female"},{"value":"Enersol"}],[{"value":"Claudine Neal"},{"value":"female"},{"value":"Sealoud"}],[{"value":"Beryl Rice"},{"value":"female"},{"value":"Velity"}],[{"value":"Wilder Gonzales"},{"value":"male"},{"value":"Geekko"}],[{"value":"Georgina Schultz"},{"value":"female"},{"value":"Suretech"}],[{"value":"Carroll Buchanan"},{"value":"male"},{"value":"Ecosys"}],[{"value":"Valarie Atkinson"},{"value":"female"},{"value":"Hopeli"}],[{"value":"Schroeder Mathews"},{"value":"male"},{"value":"Polarium"}],[{"value":"Lynda Mendoza"},{"value":"female"},{"value":"Dogspa"}],[{"value":"Sarah Massey"},{"value":"female"},{"value":"Bisba"}],[{"value":"Robles Boyle"},{"value":"male"},{"value":"Comtract"}],[{"value":"Evans Hickman"},{"value":"male"},{"value":"Parleynet"}],[{"value":"Dawson Barber"},{"value":"male"},{"value":"Dymi"}],[{"value":"Bruce Strong"},{"value":"male"},{"value":"Xyqag"}],[{"value":"Nellie Whitfield"},{"value":"female"},{"value":"Exospace"}],[{"value":"Jackson Macias"},{"value":"male"},{"value":"Aquamate"}],[{"value":"Pena Pena"},{"value":"male"},{"value":"Quarx"}],[{"value":"Lelia Gates"},{"value":"female"},{"value":"Proxsoft"}],[{"value":"Letitia Vasquez"},{"value":"female"},{"value":"Slumberia"}],[{"value":"Trevino Moreno"},{"value":"male"},{"value":"Conjurica"}],[{"value":"Barr Page"},{"value":"male"},{"value":"Apex"}],[{"value":"Kirkland Merrill"},{"value":"male"},{"value":"Utara"}],[{"value":"Blanche Conley"},{"value":"female"},{"value":"Imkan"}],[{"value":"Atkins Dunlap"},{"value":"male"},{"value":"Comveyor"}],[{"value":"Everett Foreman"},{"value":"male"},{"value":"Maineland"}],[{"value":"Gould Randolph"},{"value":"male"},{"value":"Intergeek"}],[{"value":"Kelli Leon"},{"value":"female"},{"value":"Verbus"}],[{"value":"Freda Mason"},{"value":"female"},{"value":"Accidency"}],[{"value":"Tucker Maxwell"},{"value":"male"},{"value":"Lumbrex"}],[{"value":"Yvonne Parsons"},{"value":"female"},{"value":"Zolar"}],[{"value":"Woods Key"},{"value":"male"},{"value":"Bedder"}],[{"value":"Stephens Reilly"},{"value":"male"},{"value":"Acusage"}],[{"value":"Mcfarland Sparks"},{"value":"male"},{"value":"Comvey"}],[{"value":"Jocelyn Sawyer"},{"value":"female"},{"value":"Fortean"}],[{"value":"Renee Barr"},{"value":"female"},{"value":"Kiggle"}],[{"value":"Gaines Beck"},{"value":"male"},{"value":"Sequitur"}],[{"value":"Luisa Farrell"},{"value":"female"},{"value":"Cinesanct"}],[{"value":"Robyn Strickland"},{"value":"female"},{"value":"Obones"}],[{"value":"Roseann Jarvis"},{"value":"female"},{"value":"Aquazure"}],[{"value":"Johnston Park"},{"value":"male"},{"value":"Netur"}],[{"value":"Wong Craft"},{"value":"male"},{"value":"Opticall"}],[{"value":"Merritt Cole"},{"value":"male"},{"value":"Techtrix"}],[{"value":"Dale Byrd"},{"value":"female"},{"value":"Kneedles"}],[{"value":"Sara Delgado"},{"value":"female"},{"value":"Netagy"}],[{"value":"Alisha Myers"},{"value":"female"},{"value":"Intradisk"}],[{"value":"Felecia Smith"},{"value":"female"},{"value":"Futurity"}],[{"value":"Neal Harvey"},{"value":"male"},{"value":"Pyramax"}],[{"value":"Nola Miles"},{"value":"female"},{"value":"Sonique"}],[{"value":"Herring Pierce"},{"value":"male"},{"value":"Geeketron"}],[{"value":"Shelley Rodriquez"},{"value":"female"},{"value":"Bostonic"}],[{"value":"Cora Chase"},{"value":"female"},{"value":"Isonus"}],[{"value":"Mckay Santos"},{"value":"male"},{"value":"Amtas"}],[{"value":"Hilda Crane"},{"value":"female"},{"value":"Jumpstack"}],[{"value":"Jeanne Lindsay"},{"value":"female"},{"value":"Genesynk"}],[{"value":"Frye Sharpe"},{"value":"male"},{"value":"Eplode"}],[{"value":"Velma Fry"},{"value":"female"},{"value":"Ronelon"}],[{"value":"Reyna Espinoza"},{"value":"female"},{"value":"Prismatic"}],[{"value":"Spencer Sloan"},{"value":"male"},{"value":"Comverges"}],[{"value":"Graham Marsh"},{"value":"male"},{"value":"Medifax"}],[{"value":"Hale Boone"},{"value":"male"},{"value":"Digial"}],[{"value":"Wiley Hubbard"},{"value":"male"},{"value":"Zensus"}],[{"value":"Blackburn Drake"},{"value":"male"},{"value":"Frenex"}],[{"value":"Franco Hunter"},{"value":"male"},{"value":"Rockabye"}],[{"value":"Barnett Case"},{"value":"male"},{"value":"Norali"}],[{"value":"Alexander Foley"},{"value":"male"},{"value":"Geekosis"}],[{"value":"Lynette Stein"},{"value":"female"},{"value":"Macronaut"}],[{"value":"Anthony Joyner"},{"value":"male"},{"value":"Senmei"}],[{"value":"Garrett Brennan"},{"value":"male"},{"value":"Bluegrain"}],[{"value":"Betsy Horton"},{"value":"female"},{"value":"Zilla"}],[{"value":"Patton Small"},{"value":"male"},{"value":"Genmex"}],[{"value":"Lakisha Huber"},{"value":"female"},{"value":"Insource"}],[{"value":"Lindsay Avery"},{"value":"female"},{"value":"Unq"}],[{"value":"Ayers Hood"},{"value":"male"},{"value":"Accuprint"}],[{"value":"Torres Durham"},{"value":"male"},{"value":"Uplinx"}],[{"value":"Vincent Hernandez"},{"value":"male"},{"value":"Talendula"}],[{"value":"Baird Ryan"},{"value":"male"},{"value":"Aquasseur"}],[{"value":"Georgia Mercer"},{"value":"female"},{"value":"Skyplex"}],[{"value":"Francesca Elliott"},{"value":"female"},{"value":"Nspire"}],[{"value":"Lyons Peters"},{"value":"male"},{"value":"Quinex"}],[{"value":"Kristi Brewer"},{"value":"female"},{"value":"Oronoko"}],[{"value":"Tonya Bray"},{"value":"female"},{"value":"Insuron"}],[{"value":"Valenzuela Huff"},{"value":"male"},{"value":"Applideck"}],[{"value":"Tiffany Anderson"},{"value":"female"},{"value":"Zanymax"}],[{"value":"Jerri King"},{"value":"female"},{"value":"Eventex"}],[{"value":"Rocha Meadows"},{"value":"male"},{"value":"Goko"}],[{"value":"Marcy Green"},{"value":"female"},{"value":"Pharmex"}],[{"value":"Kirk Cross"},{"value":"male"},{"value":"Portico"}],[{"value":"Hattie Mullen"},{"value":"female"},{"value":"Zilencio"}],[{"value":"Deann Bridges"},{"value":"female"},{"value":"Equitox"}],[{"value":"Chaney Roach"},{"value":"male"},{"value":"Qualitern"}],[{"value":"Consuelo Dickson"},{"value":"female"},{"value":"Poshome"}],[{"value":"Billie Rowe"},{"value":"female"},{"value":"Cemention"}],[{"value":"Bean Donovan"},{"value":"male"},{"value":"Mantro"}],[{"value":"Lancaster Patel"},{"value":"male"},{"value":"Krog"}],[{"value":"Rosa Dyer"},{"value":"female"},{"value":"Netility"}],[{"value":"Christine Compton"},{"value":"female"},{"value":"Bleeko"}],[{"value":"Milagros Finch"},{"value":"female"},{"value":"Handshake"}],[{"value":"Ericka Alvarado"},{"value":"female"},{"value":"Lyrichord"}],[{"value":"Sylvia Sosa"},{"value":"female"},{"value":"Circum"}],[{"value":"Humphrey Curtis"},{"value":"male"},{"value":"Corepan"}]]
		
		wopts = { bookType: 'xlsx', bookSST: true, type: 'binary' };
		
		var fileName = fileName || '数据导出';
		fileName += '.xlsx';
		
		var sheetName =  'Sheet1';

		var wb = XLSX.utils.book_new(), ws = uigrid_to_sheet(data, columns, title);
		XLSX.utils.book_append_sheet(wb, ws, sheetName);
		var wbout = XLSX.write(wb, wopts);
		saveAs(new Blob([s2ab(wbout)], { type: 'application/octet-stream' }), fileName);
	}
	 

	 /**
	  * 根据array数据进行报表导出
	  */
	 /*var exportSheetByArray = function(data, fileName) {
		wopts = { bookType: 'xlsx', bookSST: true, type: 'binary' };
		
		var fileName = fileName || '数据导出';
		fileName += '.xlsx';
		
		var sheetName =  'Sheet1';
	
		var wb = XLSX.utils.book_new(), ws = XLSX.utils.aoa_to_sheet(data);
		XLSX.utils.book_append_sheet(wb, ws, sheetName);
		var wbout = XLSX.write(wb, wopts);
		saveAs(new Blob([s2ab(wbout)], { type: 'application/octet-stream' }), fileName);
	}*/
	 

	/* utilities */
	function uigrid_to_sheet(data, columns, title) {
		var o = [], oo = [], i = 0, j = 0;

		/* title */
		if(title) {
			o.push([title]);
		}
		
		/*  
		 * oTable1.fnSettings().aoColumns
		 * oTable1.fnGetData()*/
		for(j = 0; j < columns.length; ++j) {
			if(columns[j].mData) {
				oo.push(get_value(columns[j]));
			}
//			if(columns[j].filter) {
//				if(columns[j].filter.split(":").length == 2) {
//					if($filter(columns[j].filter.split(":")[0].trim())) {
//						columns[j].filterFn2 = $filter(columns[j].filter.split(":")[0].trim());
//						columns[j].filterFn = function(a) {
//							return this.filterFn2(a,columns[j].filter.split(":")[1].trim());
//						}
//					}
//				} else {
//					columns[j].filterFn = $filter(columns[j].filter);
//				}
//			}
		}
		o.push(oo);

		/* table data */
		for(i = 0; i < data.length; ++i) {
			oo = [];
			for(j = 0; j < columns.length; ++j) {
				if(columns[j].mData) {
					oo.push(get_value_by_column(data[i],columns[j]));
				}
			}
			
			o.push(oo);
		}
		/* aoa_to_sheet converts an array of arrays into a worksheet object */
		return XLSX.utils.aoa_to_sheet(o);
	}

	function get_value(col) {
		if(!col) return null;
		if(col.sTitle) return col.sTitle;
		if(col.mData) return col.mData;
		return null;
	}
	
	function get_value_by_column(data,col) {
		if(!data) return null;
		if(!col)  return null;
		if(!data[col.mData]) return null;
		if(col.mRender) return col.mRender(data[col.mData],"excel_export",data);
		return data[col.mData];
	}

	// 固定  不改
	function s2ab(s) {
		if(typeof ArrayBuffer !== 'undefined') {
			var buf = new ArrayBuffer(s.length);
			var view = new Uint8Array(buf);
			for (var i=0; i!=s.length; ++i) view[i] = s.charCodeAt(i) & 0xFF;
			return buf;
		} else {
			var buf = new Array(s.length);
			for (var i=0; i!=s.length; ++i) buf[i] = s.charCodeAt(i) & 0xFF;
			return buf;
		}
	}
