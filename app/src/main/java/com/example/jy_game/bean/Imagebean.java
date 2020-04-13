package com.example.jy_game.bean;

import java.util.List;

public class Imagebean {

    /**
     * status : 200
     * message : 查询成功
     * data : [{"imageid":127,"typeid":15,"mname":"ic_floor_006","url":"http://47.110.151.50:8080/images/15-花/ic_floor_006.jpg","imgtime":"2020-04-08 00:41:14","tname":"花","music":"http://47.110.151.50:8080/mp3/花.mp3"},{"imageid":126,"typeid":15,"mname":"ic_floor_005","url":"http://47.110.151.50:8080/images/15-花/ic_floor_005.jpg","imgtime":"2020-04-08 00:41:14","tname":"花","music":"http://47.110.151.50:8080/mp3/花.mp3"},{"imageid":125,"typeid":15,"mname":"ic_floor_004","url":"http://47.110.151.50:8080/images/15-花/ic_floor_004.jpg","imgtime":"2020-04-08 00:41:14","tname":"花","music":"http://47.110.151.50:8080/mp3/花.mp3"},{"imageid":124,"typeid":15,"mname":"ic_floor_003","url":"http://47.110.151.50:8080/images/15-花/ic_floor_003.jpg","imgtime":"2020-04-08 00:41:14","tname":"花","music":"http://47.110.151.50:8080/mp3/花.mp3"},{"imageid":123,"typeid":15,"mname":"ic_floor_002","url":"http://47.110.151.50:8080/images/15-花/ic_floor_002.jpg","imgtime":"2020-04-08 00:41:14","tname":"花","music":"http://47.110.151.50:8080/mp3/花.mp3"},{"imageid":122,"typeid":15,"mname":"ic_floor_001","url":"http://47.110.151.50:8080/images/15-花/ic_floor_001.png","imgtime":"2020-04-08 00:41:14","tname":"花","music":"http://47.110.151.50:8080/mp3/花.mp3"},{"imageid":121,"typeid":14,"mname":"ic_spoon_006","url":"http://47.110.151.50:8080/images/14-勺子/ic_spoon_006.jpg","imgtime":"2020-04-08 00:40:10","tname":"勺子","music":"http://47.110.151.50:8080/mp3/勺子.mp3"},{"imageid":120,"typeid":14,"mname":"ic_spoon_005","url":"http://47.110.151.50:8080/images/14-勺子/ic_spoon_005.jpg","imgtime":"2020-04-08 00:40:10","tname":"勺子","music":"http://47.110.151.50:8080/mp3/勺子.mp3"},{"imageid":119,"typeid":14,"mname":"ic_spoon_004","url":"http://47.110.151.50:8080/images/14-勺子/ic_spoon_004.jpg","imgtime":"2020-04-08 00:40:10","tname":"勺子","music":"http://47.110.151.50:8080/mp3/勺子.mp3"},{"imageid":118,"typeid":14,"mname":"ic_spoon_003","url":"http://47.110.151.50:8080/images/14-勺子/ic_spoon_003.jpg","imgtime":"2020-04-08 00:40:10","tname":"勺子","music":"http://47.110.151.50:8080/mp3/勺子.mp3"},{"imageid":117,"typeid":14,"mname":"ic_spoon_002","url":"http://47.110.151.50:8080/images/14-勺子/ic_spoon_002.jpg","imgtime":"2020-04-08 00:40:10","tname":"勺子","music":"http://47.110.151.50:8080/mp3/勺子.mp3"},{"imageid":116,"typeid":14,"mname":"ic_spoon_001","url":"http://47.110.151.50:8080/images/14-勺子/ic_spoon_001.jpg","imgtime":"2020-04-08 00:40:10","tname":"勺子","music":"http://47.110.151.50:8080/mp3/勺子.mp3"},{"imageid":115,"typeid":13,"mname":"ic_cup_006","url":"http://47.110.151.50:8080/images/13-杯子/ic_cup_006.jpg","imgtime":"2020-04-08 00:38:57","tname":"杯子","music":"http://47.110.151.50:8080/mp3/杯子.mp3"},{"imageid":114,"typeid":13,"mname":"ic_cup_005","url":"http://47.110.151.50:8080/images/13-杯子/ic_cup_005.jpg","imgtime":"2020-04-08 00:38:57","tname":"杯子","music":"http://47.110.151.50:8080/mp3/杯子.mp3"},{"imageid":113,"typeid":13,"mname":"ic_cup_004","url":"http://47.110.151.50:8080/images/13-杯子/ic_cup_004.jpg","imgtime":"2020-04-08 00:38:57","tname":"杯子","music":"http://47.110.151.50:8080/mp3/杯子.mp3"},{"imageid":112,"typeid":13,"mname":"ic_cup_003","url":"http://47.110.151.50:8080/images/13-杯子/ic_cup_003.jpg","imgtime":"2020-04-08 00:38:57","tname":"杯子","music":"http://47.110.151.50:8080/mp3/杯子.mp3"},{"imageid":111,"typeid":13,"mname":"ic_cup_002","url":"http://47.110.151.50:8080/images/13-杯子/ic_cup_002.jpg","imgtime":"2020-04-08 00:38:57","tname":"杯子","music":"http://47.110.151.50:8080/mp3/杯子.mp3"},{"imageid":110,"typeid":13,"mname":"ic_cup_001","url":"http://47.110.151.50:8080/images/13-杯子/ic_cup_001.jpg","imgtime":"2020-04-08 00:38:57","tname":"杯子","music":"http://47.110.151.50:8080/mp3/杯子.mp3"},{"imageid":109,"typeid":12,"mname":"ic_ball_006","url":"http://47.110.151.50:8080/images/12-球/ic_ball_006.jpg","imgtime":"2020-04-08 00:31:59","tname":"球","music":"http://47.110.151.50:8080/mp3/球.mp3"},{"imageid":108,"typeid":12,"mname":"ic_ball_005","url":"http://47.110.151.50:8080/images/12-球/ic_ball_005.jpg","imgtime":"2020-04-08 00:31:59","tname":"球","music":"http://47.110.151.50:8080/mp3/球.mp3"},{"imageid":107,"typeid":12,"mname":"ic_ball_004","url":"http://47.110.151.50:8080/images/12-球/ic_ball_004.jpg","imgtime":"2020-04-08 00:31:59","tname":"球","music":"http://47.110.151.50:8080/mp3/球.mp3"},{"imageid":106,"typeid":12,"mname":"ic_ball_003","url":"http://47.110.151.50:8080/images/12-球/ic_ball_003.jpg","imgtime":"2020-04-08 00:31:59","tname":"球","music":"http://47.110.151.50:8080/mp3/球.mp3"},{"imageid":105,"typeid":12,"mname":"ic_ball_002","url":"http://47.110.151.50:8080/images/12-球/ic_ball_002.jpg","imgtime":"2020-04-08 00:31:59","tname":"球","music":"http://47.110.151.50:8080/mp3/球.mp3"},{"imageid":104,"typeid":12,"mname":"ic_ball_001","url":"http://47.110.151.50:8080/images/12-球/ic_ball_001.jpg","imgtime":"2020-04-08 00:31:59","tname":"球","music":"http://47.110.151.50:8080/mp3/球.mp3"},{"imageid":103,"typeid":11,"mname":"ic_bike_006","url":"http://47.110.151.50:8080/images/11-自行车/ic_bike_006.jpg","imgtime":"2020-04-08 00:30:50","tname":"自行车","music":"http://47.110.151.50:8080/mp3/自行车.mp3"},{"imageid":102,"typeid":11,"mname":"ic_bike_005","url":"http://47.110.151.50:8080/images/11-自行车/ic_bike_005.jpg","imgtime":"2020-04-08 00:30:50","tname":"自行车","music":"http://47.110.151.50:8080/mp3/自行车.mp3"},{"imageid":101,"typeid":11,"mname":"ic_bike_004","url":"http://47.110.151.50:8080/images/11-自行车/ic_bike_004.jpg","imgtime":"2020-04-08 00:30:50","tname":"自行车","music":"http://47.110.151.50:8080/mp3/自行车.mp3"},{"imageid":100,"typeid":11,"mname":"ic_bike_003","url":"http://47.110.151.50:8080/images/11-自行车/ic_bike_003.jpg","imgtime":"2020-04-08 00:30:50","tname":"自行车","music":"http://47.110.151.50:8080/mp3/自行车.mp3"},{"imageid":99,"typeid":11,"mname":"ic_bike_002","url":"http://47.110.151.50:8080/images/11-自行车/ic_bike_002.jpg","imgtime":"2020-04-08 00:30:50","tname":"自行车","music":"http://47.110.151.50:8080/mp3/自行车.mp3"},{"imageid":98,"typeid":11,"mname":"ic_bike_001","url":"http://47.110.151.50:8080/images/11-自行车/ic_bike_001.jpg","imgtime":"2020-04-08 00:30:50","tname":"自行车","music":"http://47.110.151.50:8080/mp3/自行车.mp3"},{"imageid":97,"typeid":10,"mname":"ic_bed_001","url":"http://47.110.151.50:8080/images/10-床/ic_bed_006.jpg","imgtime":"2020-04-08 00:29:35","tname":"床","music":"http://47.110.151.50:8080/mp3/床.mp3"},{"imageid":96,"typeid":10,"mname":"ic_bed_001","url":"http://47.110.151.50:8080/images/10-床/ic_bed_005.jpg","imgtime":"2020-04-08 00:29:35","tname":"床","music":"http://47.110.151.50:8080/mp3/床.mp3"},{"imageid":95,"typeid":10,"mname":"ic_bed_001","url":"http://47.110.151.50:8080/images/10-床/ic_bed_004.jpg","imgtime":"2020-04-08 00:29:34","tname":"床","music":"http://47.110.151.50:8080/mp3/床.mp3"},{"imageid":94,"typeid":10,"mname":"ic_bed_001","url":"http://47.110.151.50:8080/images/10-床/ic_bed_003.jpg","imgtime":"2020-04-08 00:29:34","tname":"床","music":"http://47.110.151.50:8080/mp3/床.mp3"},{"imageid":93,"typeid":10,"mname":"ic_bed_001","url":"http://47.110.151.50:8080/images/10-床/ic_bed_002.jpg","imgtime":"2020-04-08 00:29:34","tname":"床","music":"http://47.110.151.50:8080/mp3/床.mp3"},{"imageid":92,"typeid":10,"mname":"ic_bed_001","url":"http://47.110.151.50:8080/images/10-床/ic_bed_001.jpg","imgtime":"2020-04-08 00:29:34","tname":"床","music":"http://47.110.151.50:8080/mp3/床.mp3"},{"imageid":91,"typeid":9,"mname":"ic_chair_006","url":"http://47.110.151.50:8080/images/09-椅子/ic_chair_006.jpg","imgtime":"2020-04-08 00:16:51","tname":"椅子","music":"http://47.110.151.50:8080/mp3/椅子.mp3"},{"imageid":90,"typeid":9,"mname":"ic_chair_005","url":"http://47.110.151.50:8080/images/09-椅子/ic_chair_005.jpg","imgtime":"2020-04-08 00:16:51","tname":"椅子","music":"http://47.110.151.50:8080/mp3/椅子.mp3"},{"imageid":89,"typeid":9,"mname":"ic_chair_004","url":"http://47.110.151.50:8080/images/09-椅子/ic_chair_004.jpg","imgtime":"2020-04-08 00:16:51","tname":"椅子","music":"http://47.110.151.50:8080/mp3/椅子.mp3"},{"imageid":88,"typeid":9,"mname":"ic_chair_003","url":"http://47.110.151.50:8080/images/09-椅子/ic_chair_003.jpg","imgtime":"2020-04-08 00:16:51","tname":"椅子","music":"http://47.110.151.50:8080/mp3/椅子.mp3"},{"imageid":87,"typeid":9,"mname":"ic_chair_002","url":"http://47.110.151.50:8080/images/09-椅子/ic_chair_002.jpg","imgtime":"2020-04-08 00:16:51","tname":"椅子","music":"http://47.110.151.50:8080/mp3/椅子.mp3"},{"imageid":86,"typeid":9,"mname":"ic_chair_001","url":"http://47.110.151.50:8080/images/09-椅子/ic_chair_001.jpg","imgtime":"2020-04-08 00:16:51","tname":"椅子","music":"http://47.110.151.50:8080/mp3/椅子.mp3"},{"imageid":85,"typeid":8,"mname":"ic_sweets_006","url":"http://47.110.151.50:8080/images/08-糖果/ic_sweets_006.jpg","imgtime":"2020-04-08 00:09:13","tname":"糖果","music":"http://47.110.151.50:8080/mp3/糖果.mp3"},{"imageid":84,"typeid":8,"mname":"ic_sweets_005","url":"http://47.110.151.50:8080/images/08-糖果/ic_sweets_005.jpg","imgtime":"2020-04-08 00:09:13","tname":"糖果","music":"http://47.110.151.50:8080/mp3/糖果.mp3"},{"imageid":83,"typeid":8,"mname":"ic_sweets_004","url":"http://47.110.151.50:8080/images/08-糖果/ic_sweets_004.jpg","imgtime":"2020-04-08 00:09:13","tname":"糖果","music":"http://47.110.151.50:8080/mp3/糖果.mp3"},{"imageid":82,"typeid":8,"mname":"ic_sweets_003","url":"http://47.110.151.50:8080/images/08-糖果/ic_sweets_003.jpg","imgtime":"2020-04-08 00:09:13","tname":"糖果","music":"http://47.110.151.50:8080/mp3/糖果.mp3"},{"imageid":81,"typeid":8,"mname":"ic_sweets_002","url":"http://47.110.151.50:8080/images/08-糖果/ic_sweets_002.jpg","imgtime":"2020-04-08 00:09:13","tname":"糖果","music":"http://47.110.151.50:8080/mp3/糖果.mp3"},{"imageid":80,"typeid":8,"mname":"ic_sweets_001","url":"http://47.110.151.50:8080/images/08-糖果/ic_sweets_001.jpg","imgtime":"2020-04-08 00:09:13","tname":"糖果","music":"http://47.110.151.50:8080/mp3/糖果.mp3"},{"imageid":79,"typeid":7,"mname":"ic_shoe_006","url":"http://47.110.151.50:8080/images/07-鞋/ic_shoe_006.jpg","imgtime":"2020-04-08 00:07:33","tname":"鞋","music":"http://47.110.151.50:8080/mp3/鞋.mp3"},{"imageid":78,"typeid":7,"mname":"ic_shoe_005","url":"http://47.110.151.50:8080/images/07-鞋/ic_shoe_005.jpg","imgtime":"2020-04-08 00:07:33","tname":"鞋","music":"http://47.110.151.50:8080/mp3/鞋.mp3"},{"imageid":77,"typeid":7,"mname":"ic_shoe_004","url":"http://47.110.151.50:8080/images/07-鞋/ic_shoe_004.jpg","imgtime":"2020-04-08 00:07:33","tname":"鞋","music":"http://47.110.151.50:8080/mp3/鞋.mp3"},{"imageid":76,"typeid":7,"mname":"ic_shoe_003","url":"http://47.110.151.50:8080/images/07-鞋/ic_shoe_003.jpg","imgtime":"2020-04-08 00:07:33","tname":"鞋","music":"http://47.110.151.50:8080/mp3/鞋.mp3"},{"imageid":75,"typeid":7,"mname":"ic_shoe_002","url":"http://47.110.151.50:8080/images/07-鞋/ic_shoe_002.jpg","imgtime":"2020-04-08 00:07:33","tname":"鞋","music":"http://47.110.151.50:8080/mp3/鞋.mp3"},{"imageid":74,"typeid":7,"mname":"ic_shoe_001","url":"http://47.110.151.50:8080/images/07-鞋/ic_shoe_001.jpg","imgtime":"2020-04-08 00:07:33","tname":"鞋","music":"http://47.110.151.50:8080/mp3/鞋.mp3"},{"imageid":73,"typeid":6,"mname":"ic_car_006","url":"http://47.110.151.50:8080/images/06-车/ic_car_006.jpg","imgtime":"2020-04-08 00:05:28","tname":"车","music":"http://47.110.151.50:8080/mp3/车.mp3"},{"imageid":72,"typeid":6,"mname":"ic_car_005","url":"http://47.110.151.50:8080/images/06-车/ic_car_005.jpg","imgtime":"2020-04-08 00:05:28","tname":"车","music":"http://47.110.151.50:8080/mp3/车.mp3"},{"imageid":71,"typeid":6,"mname":"ic_car_004","url":"http://47.110.151.50:8080/images/06-车/ic_car_004.jpg","imgtime":"2020-04-08 00:05:28","tname":"车","music":"http://47.110.151.50:8080/mp3/车.mp3"},{"imageid":70,"typeid":6,"mname":"ic_car_003","url":"http://47.110.151.50:8080/images/06-车/ic_car_003.jpg","imgtime":"2020-04-08 00:05:28","tname":"车","music":"http://47.110.151.50:8080/mp3/车.mp3"},{"imageid":69,"typeid":6,"mname":"ic_car_002","url":"http://47.110.151.50:8080/images/06-车/ic_car_002.jpg","imgtime":"2020-04-08 00:05:28","tname":"车","music":"http://47.110.151.50:8080/mp3/车.mp3"},{"imageid":68,"typeid":6,"mname":"ic_car_001","url":"http://47.110.151.50:8080/images/06-车/ic_car_001.jpg","imgtime":"2020-04-08 00:05:28","tname":"车","music":"http://47.110.151.50:8080/mp3/车.mp3"},{"imageid":67,"typeid":5,"mname":"ic_airplane_006","url":"http://47.110.151.50:8080/images/05-飞机/ic_airplane_006.jpg","imgtime":"2020-04-07 21:02:49","tname":"飞机","music":"http://47.110.151.50:8080/mp3/飞机.mp3"},{"imageid":66,"typeid":5,"mname":"ic_airplane_005","url":"http://47.110.151.50:8080/images/05-飞机/ic_airplane_005.jpg","imgtime":"2020-04-07 21:02:49","tname":"飞机","music":"http://47.110.151.50:8080/mp3/飞机.mp3"},{"imageid":65,"typeid":5,"mname":"ic_airplane_004","url":"http://47.110.151.50:8080/images/05-飞机/ic_airplane_004.jpg","imgtime":"2020-04-07 21:02:49","tname":"飞机","music":"http://47.110.151.50:8080/mp3/飞机.mp3"},{"imageid":64,"typeid":5,"mname":"ic_airplane_003","url":"http://47.110.151.50:8080/images/05-飞机/ic_airplane_003.jpg","imgtime":"2020-04-07 21:02:49","tname":"飞机","music":"http://47.110.151.50:8080/mp3/飞机.mp3"},{"imageid":63,"typeid":5,"mname":"ic_airplane_002","url":"http://47.110.151.50:8080/images/05-飞机/ic_airplane_002.jpg","imgtime":"2020-04-07 21:02:49","tname":"飞机","music":"http://47.110.151.50:8080/mp3/飞机.mp3"},{"imageid":62,"typeid":5,"mname":"ic_airplane_001","url":"http://47.110.151.50:8080/images/05-飞机/ic_airplane_001.jpg","imgtime":"2020-04-07 21:02:49","tname":"飞机","music":"http://47.110.151.50:8080/mp3/飞机.mp3"},{"imageid":61,"typeid":4,"mname":"ic_bird_006","url":"http://47.110.151.50:8080/images/04-鸟/ic_bird_006.jpg","imgtime":"2020-04-07 20:59:16","tname":"鸟","music":"http://47.110.151.50:8080/mp3/鸟.mp3"},{"imageid":60,"typeid":4,"mname":"ic_bird_005","url":"http://47.110.151.50:8080/images/04-鸟/ic_bird_005.jpg","imgtime":"2020-04-07 20:59:16","tname":"鸟","music":"http://47.110.151.50:8080/mp3/鸟.mp3"},{"imageid":59,"typeid":4,"mname":"ic_bird_004","url":"http://47.110.151.50:8080/images/04-鸟/ic_bird_004.jpg","imgtime":"2020-04-07 20:59:16","tname":"鸟","music":"http://47.110.151.50:8080/mp3/鸟.mp3"},{"imageid":58,"typeid":4,"mname":"ic_bird_003","url":"http://47.110.151.50:8080/images/04-鸟/ic_bird_003.jpg","imgtime":"2020-04-07 20:59:16","tname":"鸟","music":"http://47.110.151.50:8080/mp3/鸟.mp3"},{"imageid":57,"typeid":4,"mname":"ic_bird_002","url":"http://47.110.151.50:8080/images/04-鸟/ic_bird_002.jpg","imgtime":"2020-04-07 20:59:16","tname":"鸟","music":"http://47.110.151.50:8080/mp3/鸟.mp3"},{"imageid":56,"typeid":4,"mname":"ic_bird_001","url":"http://47.110.151.50:8080/images/04-鸟/ic_bird_001.jpg","imgtime":"2020-04-07 20:59:16","tname":"鸟","music":"http://47.110.151.50:8080/mp3/鸟.mp3"},{"imageid":55,"typeid":3,"mname":"ic_cat_006","url":"http://47.110.151.50:8080/images/03-3猫/ic_cat_006.jpg","imgtime":"2020-04-07 20:57:20","tname":"猫","music":"http://47.110.151.50:8080/mp3/猫.mp3"},{"imageid":54,"typeid":3,"mname":"ic_cat_005","url":"http://47.110.151.50:8080/images/03-3猫/ic_cat_005.jpg","imgtime":"2020-04-07 20:57:20","tname":"猫","music":"http://47.110.151.50:8080/mp3/猫.mp3"},{"imageid":53,"typeid":3,"mname":"ic_cat_004","url":"http://47.110.151.50:8080/images/03-3猫/ic_cat_004.jpg","imgtime":"2020-04-07 20:57:20","tname":"猫","music":"http://47.110.151.50:8080/mp3/猫.mp3"},{"imageid":52,"typeid":3,"mname":"ic_cat_003","url":"http://47.110.151.50:8080/images/03-3猫/ic_cat_003.jpg","imgtime":"2020-04-07 20:57:20","tname":"猫","music":"http://47.110.151.50:8080/mp3/猫.mp3"},{"imageid":51,"typeid":3,"mname":"ic_cat_002","url":"http://47.110.151.50:8080/images/03-3猫/ic_cat_002.jpg","imgtime":"2020-04-07 20:57:20","tname":"猫","music":"http://47.110.151.50:8080/mp3/猫.mp3"},{"imageid":50,"typeid":3,"mname":"ic_cat_001","url":"http://47.110.151.50:8080/images/03-3猫/ic_cat_001.jpg","imgtime":"2020-04-07 20:57:20","tname":"猫","music":"http://47.110.151.50:8080/mp3/猫.mp3"},{"imageid":49,"typeid":2,"mname":"ic_cookie_006","url":"http://47.110.151.50:8080/images/02-饼干/ic_cookie_006.jpg","imgtime":"2020-04-07 20:53:28","tname":"饼干","music":"http://47.110.151.50:8080/mp3/饼干.mp3"},{"imageid":48,"typeid":2,"mname":"ic_cookie_005","url":"http://47.110.151.50:8080/images/02-饼干/ic_cookie_005.jpg","imgtime":"2020-04-07 20:53:28","tname":"饼干","music":"http://47.110.151.50:8080/mp3/饼干.mp3"},{"imageid":47,"typeid":2,"mname":"ic_cookie_004","url":"http://47.110.151.50:8080/images/02-饼干/ic_cookie_004.jpg","imgtime":"2020-04-07 20:53:28","tname":"饼干","music":"http://47.110.151.50:8080/mp3/饼干.mp3"},{"imageid":46,"typeid":2,"mname":"ic_cookie_003","url":"http://47.110.151.50:8080/images/02-饼干/ic_cookie_003.jpg","imgtime":"2020-04-07 20:53:28","tname":"饼干","music":"http://47.110.151.50:8080/mp3/饼干.mp3"},{"imageid":45,"typeid":2,"mname":"ic_cookie_002","url":"http://47.110.151.50:8080/images/02-饼干/ic_cookie_002.jpg","imgtime":"2020-04-07 20:53:28","tname":"饼干","music":"http://47.110.151.50:8080/mp3/饼干.mp3"},{"imageid":44,"typeid":2,"mname":"ic_cookie_001","url":"http://47.110.151.50:8080/images/02-饼干/ic_cookie_001.jpg","imgtime":"2020-04-07 20:53:28","tname":"饼干","music":"http://47.110.151.50:8080/mp3/饼干.mp3"},{"imageid":43,"typeid":1,"mname":"ic_apple_006","url":"http://47.110.151.50:8080/images/01-苹果/ic_apple_006.jpg","imgtime":"2020-04-07 20:46:43","tname":"苹果","music":"http://47.110.151.50:8080/mp3/苹果.mp3"},{"imageid":42,"typeid":1,"mname":"ic_apple_005","url":"http://47.110.151.50:8080/images/01-苹果/ic_apple_005.jpg","imgtime":"2020-04-07 20:46:43","tname":"苹果","music":"http://47.110.151.50:8080/mp3/苹果.mp3"},{"imageid":41,"typeid":1,"mname":"ic_apple_004","url":"http://47.110.151.50:8080/images/01-苹果/ic_apple_004.jpg","imgtime":"2020-04-07 20:46:43","tname":"苹果","music":"http://47.110.151.50:8080/mp3/苹果.mp3"},{"imageid":40,"typeid":1,"mname":"ic_apple_003","url":"http://47.110.151.50:8080/images/01-苹果/ic_apple_003.jpg","imgtime":"2020-04-07 20:46:43","tname":"苹果","music":"http://47.110.151.50:8080/mp3/苹果.mp3"},{"imageid":39,"typeid":1,"mname":"ic_apple_002","url":"http://47.110.151.50:8080/images/01-苹果/ic_apple_002.jpg","imgtime":"2020-04-07 20:46:43","tname":"苹果","music":"http://47.110.151.50:8080/mp3/苹果.mp3"},{"imageid":38,"typeid":1,"mname":"ic_apple_001","url":"http://47.110.151.50:8080/images/01-苹果/ic_apple_001.jpg","imgtime":"2020-04-07 20:46:43","tname":"苹果","music":"http://47.110.151.50:8080/mp3/苹果.mp3"}]
     * version : 2
     * typeCount : 15
     */

    private int status;
    private String message;
    private int version;
    private int typeCount;
    private List<DataBean> data;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public int getTypeCount() {
        return typeCount;
    }

    public void setTypeCount(int typeCount) {
        this.typeCount = typeCount;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * imageid : 127
         * typeid : 15
         * mname : ic_floor_006
         * url : http://47.110.151.50:8080/images/15-花/ic_floor_006.jpg
         * imgtime : 2020-04-08 00:41:14
         * tname : 花
         * music : http://47.110.151.50:8080/mp3/花.mp3
         */

        private int imageid;
        private int typeid;
        private String mname;
        private String url;
        private String imgtime;
        private String tname;
        private String music;

        public int getImageid() {
            return imageid;
        }

        public void setImageid(int imageid) {
            this.imageid = imageid;
        }

        public int getTypeid() {
            return typeid;
        }

        public void setTypeid(int typeid) {
            this.typeid = typeid;
        }

        public String getMname() {
            return mname;
        }

        public void setMname(String mname) {
            this.mname = mname;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getImgtime() {
            return imgtime;
        }

        public void setImgtime(String imgtime) {
            this.imgtime = imgtime;
        }

        public String getTname() {
            return tname;
        }

        public void setTname(String tname) {
            this.tname = tname;
        }

        public String getMusic() {
            return music;
        }

        public void setMusic(String music) {
            this.music = music;
        }
    }
}
