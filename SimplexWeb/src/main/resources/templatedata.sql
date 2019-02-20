--########################################   PRODUCT   ######################################## 

--Product Type Table
INSERT INTO `product_type_tbl` (`product_type_id`, `product_type_name`, `product_type_abbr`) VALUES (1, 'FIBC', 'FIBC') ;

--Product Model Type Table
INSERT INTO `product_model_type_tbl` (`model_type_id`, `model_type_name`, `model_type_abbr`) VALUES (1, '4 Panel', '4PANEL');
INSERT INTO `product_model_type_tbl` (`model_type_id`, `model_type_name`, `model_type_abbr`) VALUES (2, 'U PANEL', 'UPANEL');
INSERT INTO `product_model_type_tbl` (`model_type_id`, `model_type_name`, `model_type_abbr`) VALUES (3, 'Tubular/Circular', 'TUBULAR_CIRCULAR');
INSERT INTO `product_model_type_tbl` (`model_type_id`, `model_type_name`, `model_type_abbr`) VALUES (4, 'Single Loop', 'SINGLE_LOOP');
INSERT INTO `product_model_type_tbl` (`model_type_id`, `model_type_name`, `model_type_abbr`) VALUES (5, 'Two Loop', 'TWO_LOOP');
INSERT INTO `product_model_type_tbl` (`model_type_id`, `model_type_name`, `model_type_abbr`) VALUES (6, 'Q Bag/ Baffle', 'Q_BAFFLE');
INSERT INTO `product_model_type_tbl` (`model_type_id`, `model_type_name`, `model_type_abbr`) VALUES (7, 'Hood', 'HOOD');
INSERT INTO `product_model_type_tbl` (`model_type_id`, `model_type_name`, `model_type_abbr`) VALUES (8, 'Container Liner', 'CONTAINER_LINER');
INSERT INTO `product_model_type_tbl` (`model_type_id`, `model_type_name`, `model_type_abbr`) VALUES (9, 'Platen', 'PLATEN');

--Product Unit Type Length Table
INSERT INTO `product_unit_type_length_tbl` (`unit_type_id`, `unit_type_abbr`, `unit_type_name`) VALUES (1, 'CMS', 'cms');
INSERT INTO `product_unit_type_length_tbl` (`unit_type_id`, `unit_type_abbr`, `unit_type_name`) VALUES (2, 'INCHES', 'inches');
INSERT INTO `product_unit_type_length_tbl` (`unit_type_id`, `unit_type_abbr`, `unit_type_name`) VALUES (3, 'MILLIMETER', 'millimeter');

--Product Unit Type Weight Table
INSERT INTO `product_unit_type_weight_tbl` (`unit_type_id`, `unit_type_name`, `unit_type_abbr`) VALUES (1, 'GSM', 'GSM');
INSERT INTO `product_unit_type_weight_tbl` (`unit_type_id`, `unit_type_name`, `unit_type_abbr`) VALUES (2, 'Ounces', 'OUNCES');

--Product Surface Type Table
INSERT INTO `product_surface_type_tbl` (`surface_type_id`, `surface_type_name`, `surface_type_abbr`) VALUES (1, 'Internal', 'INTERNAL');
INSERT INTO `product_surface_type_tbl` (`surface_type_id`, `surface_type_name`, `surface_type_abbr`) VALUES (2, 'External', 'EXTERNAL');

--Product SF Type Table
INSERT INTO `product_sf_type_tbl` (`sf_type_id`, `sf_type_name`, `sf_type_abbr`, `sf_type_value`) VALUES (1, '6:1', 'SIX_BY_ONE', 6);
INSERT INTO `product_sf_type_tbl` (`sf_type_id`, `sf_type_name`, `sf_type_abbr`, `sf_type_value`) VALUES (2, '5:1', 'FIVE_BY_ONE', 5);




--########################################   FABRIC   ######################################## 

--Fabric Type Table
INSERT INTO `fabric_type_tbl` (`fabric_type_id`, `fabric_type_abbr`,  `fabric_type_name`) VALUES (1, 'FLAT_FABRIC', 'Flat Fabric');
INSERT INTO `fabric_type_tbl` (`fabric_type_id`, `fabric_type_abbr`,  `fabric_type_name`) VALUES (2, 'TUBULAR_FABRIC', 'Tubular Fabric');
INSERT INTO `fabric_type_tbl` (`fabric_type_id`, `fabric_type_abbr`,  `fabric_type_name`) VALUES (3, 'VENTILATED_FABRIC', 'Ventilated Fabric');
INSERT INTO `fabric_type_tbl` (`fabric_type_id`, `fabric_type_abbr`,  `fabric_type_name`) VALUES (4, 'SULZER_FABRIC', 'Sulzer Fabric');
INSERT INTO `fabric_type_tbl` (`fabric_type_id`, `fabric_type_abbr`,  `fabric_type_name`) VALUES (5, 'RASCHEL_FABRIC', 'Raschel Fabric');
INSERT INTO `fabric_type_tbl` (`fabric_type_id`, `fabric_type_abbr`,  `fabric_type_name`) VALUES (6, 'LENO_FABRIC', 'Leno Fabric');
INSERT INTO `fabric_type_tbl` (`fabric_type_id`, `fabric_type_abbr`,  `fabric_type_name`) VALUES (7, 'CONDUCTIVE_FABRIC', 'Conductive Fabric');
INSERT INTO `fabric_type_tbl` (`fabric_type_id`, `fabric_type_abbr`,  `fabric_type_name`) VALUES (8, 'BTYPE_FABRIC', 'B-Type Fabric');

--Fabric Color Table
INSERT INTO `fabric_color_tbl` (`fabric_color_id`, `fabric_color_hash`, `fabric_color_name`) VALUES (1, 'FFFFFF', 'WHITE') ;
INSERT INTO `fabric_color_tbl` (`fabric_color_id`, `fabric_color_hash`, `fabric_color_name`) VALUES (2, 'FF0000', 'RED') ;
INSERT INTO `fabric_color_tbl` (`fabric_color_id`, `fabric_color_hash`, `fabric_color_name`) VALUES (3, 'FFA500', 'ORANGE') ;
INSERT INTO `fabric_color_tbl` (`fabric_color_id`, `fabric_color_hash`, `fabric_color_name`) VALUES (4, '00FF00', 'GREEN') ;
INSERT INTO `fabric_color_tbl` (`fabric_color_id`, `fabric_color_hash`, `fabric_color_name`) VALUES (5, 'FFFF00', 'YELLOW') ;
INSERT INTO `fabric_color_tbl` (`fabric_color_id`, `fabric_color_hash`, `fabric_color_name`) VALUES (6, '0000FF', 'BLUE') ;
INSERT INTO `fabric_color_tbl` (`fabric_color_id`, `fabric_color_hash`, `fabric_color_name`) VALUES (7, '808080', 'GREY') ;

--Fabric Gsm Type Table
INSERT INTO `fabric_gsm_type_tbl` (`fabric_gsm_type_id`, `fabric_gsm_type_abbr`, `fabric_gsm_type_name`) VALUES (1, 'UNCOATED', 'Uncoated');
INSERT INTO `fabric_gsm_type_tbl` (`fabric_gsm_type_id`, `fabric_gsm_type_abbr`, `fabric_gsm_type_name`) VALUES (2, 'COATED', 'Coated');

--Fabric Bag Seam Type Table
INSERT INTO `fabric_bag_seam_type_tbl` (`bag_seam_type_id`, `bag_seam_type_abbr`, `bag_seam_type_name`) VALUES (1, 'OVERLOCK_CHAIN', 'Overlock + Chain');
INSERT INTO `fabric_bag_seam_type_tbl` (`bag_seam_type_id`, `bag_seam_type_abbr`, `bag_seam_type_name`) VALUES (2, 'SINGLE_FILLER_CORD', 'Single Filler Cord');
INSERT INTO `fabric_bag_seam_type_tbl` (`bag_seam_type_id`, `bag_seam_type_abbr`, `bag_seam_type_name`) VALUES (3, 'DOUBLE_FILLER_CORD', 'Double Filler Cord');
INSERT INTO `fabric_bag_seam_type_tbl` (`bag_seam_type_id`, `bag_seam_type_abbr`, `bag_seam_type_name`) VALUES (4, 'TRIPLE_FILLER_CORD', 'Triple Filler Cord');
INSERT INTO `fabric_bag_seam_type_tbl` (`bag_seam_type_id`, `bag_seam_type_abbr`, `bag_seam_type_name`) VALUES (5, 'DOUBLE_NEEDLE', 'Double Needle');

--Fabric Bag Seam Color Table
INSERT INTO `fabric_bag_seam_color_tbl` (`bag_seam_color_id`, `bag_seam_color_name`, `bag_seam_color_hash`) VALUES (1, 'WHITE', 'FFFFFF') ;
INSERT INTO `fabric_bag_seam_color_tbl` (`bag_seam_color_id`, `bag_seam_color_name`, `bag_seam_color_hash`) VALUES (2, 'RED', 'FF0000') ;
INSERT INTO `fabric_bag_seam_color_tbl` (`bag_seam_color_id`, `bag_seam_color_name`, `bag_seam_color_hash`) VALUES (3, 'ORANGE', 'FFA500') ;
INSERT INTO `fabric_bag_seam_color_tbl` (`bag_seam_color_id`, `bag_seam_color_name`, `bag_seam_color_hash`) VALUES (4, 'GREEN', '00FF00') ;
INSERT INTO `fabric_bag_seam_color_tbl` (`bag_seam_color_id`, `bag_seam_color_name`, `bag_seam_color_hash`) VALUES (5, 'YELLOW', 'FFFF00') ;
INSERT INTO `fabric_bag_seam_color_tbl` (`bag_seam_color_id`, `bag_seam_color_name`, `bag_seam_color_hash`) VALUES (6, 'BLUE', '0000FF') ;
INSERT INTO `fabric_bag_seam_color_tbl` (`bag_seam_color_id`, `bag_seam_color_name`, `bag_seam_color_hash`) VALUES (7, 'GREY', '808080') ;




--########################################   TOP FILLING   ######################################## 

--Top Filling Type Table
INSERT INTO `top_filling_type_tbl` (`top_filling_type_id`, `top_filling_type_abbr`, `top_filling_type_name`) VALUES (1, 'UNCOATED', 'Uncoated');
INSERT INTO `top_filling_type_tbl` (`top_filling_type_id`, `top_filling_type_abbr`, `top_filling_type_name`) VALUES (2, 'COATED', 'Coated');

--Top Type Table
INSERT INTO `top_type_tbl` (`top_type_id`, `top_type_name`, `top_type_abbr`) values (1, 'Top Skirt', 'TOP_SKIRT');
INSERT INTO `top_type_tbl` (`top_type_id`, `top_type_name`, `top_type_abbr`) values (2, 'Top Spout', 'TOP_SPOUT');
INSERT INTO `top_type_tbl` (`top_type_id`, `top_type_name`, `top_type_abbr`) values (3, 'Top Flap', 'TOP_FLAP');
INSERT INTO `top_type_tbl` (`top_type_id`, `top_type_name`, `top_type_abbr`) values (4, 'Top Skirt With Flap', 'TOP_SKIRT_WITH_FLAP');
INSERT INTO `top_type_tbl` (`top_type_id`, `top_type_name`, `top_type_abbr`) values (5, 'Top Spout With Flap', 'TOP_SPOUT_WITH_FLAP');
INSERT INTO `top_type_tbl` (`top_type_id`, `top_type_name`, `top_type_abbr`) values (6, 'Conical Top', 'CONICAL_TOP');
INSERT INTO `top_type_tbl` (`top_type_id`, `top_type_name`, `top_type_abbr`) values (7, 'Pleated Top', 'PLEATED_TOP');
INSERT INTO `top_type_tbl` (`top_type_id`, `top_type_name`, `top_type_abbr`) values (8, 'Conical Top With Flap', 'CONICAT_TOP_WITH_FLAP');
INSERT INTO `top_type_tbl` (`top_type_id`, `top_type_name`, `top_type_abbr`) values (9, 'Pleated Top With Flap', 'PLEATED_TOP_WITH_FLAP');




--########################################   BOTTOM   ######################################## 

--Bottom Discharge Type Table
INSERT INTO `bottom_discharge_type_tbl` (`bottom_discharge_type_id`, `bottom_discharge_type_abbr`, `bottom_discharge_type_name`) VALUES (1, 'UNCOATED', 'Uncoated');
INSERT INTO `bottom_discharge_type_tbl` (`bottom_discharge_type_id`, `bottom_discharge_type_abbr`, `bottom_discharge_type_name`) VALUES (2, 'COATED', 'Coated');

--Bottom Type Table
INSERT INTO `bottom_type_tbl` (`bottom_type_id`, `bottom_type_abbr`, `bottom_type_name`) VALUES (1, 'BOTTOM_SPOUT_STANDARD', 'Bottom Spout Standard');
INSERT INTO `bottom_type_tbl` (`bottom_type_id`, `bottom_type_abbr`, `bottom_type_name`) VALUES (2, 'BOTTOM_SPOUT_PETAL', 'Bottom Spout Petal');
INSERT INTO `bottom_type_tbl` (`bottom_type_id`, `bottom_type_abbr`, `bottom_type_name`) VALUES (3, 'DISCHARGE_SPOUT', 'Discharge Spout');
INSERT INTO `bottom_type_tbl` (`bottom_type_id`, `bottom_type_abbr`, `bottom_type_name`) VALUES (4, 'EMPTY_SPOUT_WITH_IRIS', 'Emptying Spout with Iris');
INSERT INTO `bottom_type_tbl` (`bottom_type_id`, `bottom_type_abbr`, `bottom_type_name`) VALUES (5, 'PYJAMA', 'Pyjama');
INSERT INTO `bottom_type_tbl` (`bottom_type_id`, `bottom_type_abbr`, `bottom_type_name`) VALUES (6, 'BONNET_CLOSURE', 'Bonnet Closure');
INSERT INTO `bottom_type_tbl` (`bottom_type_id`, `bottom_type_abbr`, `bottom_type_name`) VALUES (7, 'CONICAL_BOTTOM', 'Conical Bottom');
INSERT INTO `bottom_type_tbl` (`bottom_type_id`, `bottom_type_abbr`, `bottom_type_name`) VALUES (8, 'FULL_DISCHARGE_BOTTOM', 'Full Discharge Bottom ');




--########################################   LOOP   ######################################## 

--Loop type Table
INSERT INTO `loop_type_tbl` (`loop_type_id`, `loop_type_abbr`, `loop_type_name`) VALUES (1, 'CORNER_LOOP', 'Corner Loop');
INSERT INTO `loop_type_tbl` (`loop_type_id`, `loop_type_abbr`, `loop_type_name`) VALUES (2, 'CROSS_CORNER_LOOP', 'Cross Corner Loop');

--Loop Material Table
INSERT INTO `loop_material_tbl` (`loop_material_id`, `loop_material_abbr`, `loop_material_name`) VALUES (1, 'MULTIFILAMENT_LOOP', 'Multifilament Loop');
INSERT INTO `loop_material_tbl` (`loop_material_id`, `loop_material_abbr`, `loop_material_name`) VALUES (2, 'FABRILATED', 'Fabrilated');
INSERT INTO `loop_material_tbl` (`loop_material_id`, `loop_material_abbr`, `loop_material_name`) VALUES (3, 'PP_LOOP', 'PP Loop');
INSERT INTO `loop_material_tbl` (`loop_material_id`, `loop_material_abbr`, `loop_material_name`) VALUES (4, 'POLYSTER', 'Polyster');

--Loop Color Table
INSERT INTO `loop_color_tbl` (`loop_color_id`, `loop_color_name`, `loop_color_hash`) VALUES (1, 'WHITE', 'FFFFFF') ;
INSERT INTO `loop_color_tbl` (`loop_color_id`, `loop_color_name`, `loop_color_hash`) VALUES (2, 'RED', 'FF0000') ;
INSERT INTO `loop_color_tbl` (`loop_color_id`, `loop_color_name`, `loop_color_hash`) VALUES (3, 'ORANGE', 'FFA500') ;
INSERT INTO `loop_color_tbl` (`loop_color_id`, `loop_color_name`, `loop_color_hash`) VALUES (4, 'GREEN', '00FF00') ;
INSERT INTO `loop_color_tbl` (`loop_color_id`, `loop_color_name`, `loop_color_hash`) VALUES (5, 'YELLOW', 'FFFF00') ;
INSERT INTO `loop_color_tbl` (`loop_color_id`, `loop_color_name`, `loop_color_hash`) VALUES (6, 'BLUE', '0000FF') ;
INSERT INTO `loop_color_tbl` (`loop_color_id`, `loop_color_name`, `loop_color_hash`) VALUES (7, 'GREY', '808080') ;

--Loop Sewing Type Table
INSERT INTO `loop_sewing_type_tbl` (`loop_sewing_type_id`, `loop_sewing_type_abbr`, `loop_sewing_type_name`) VALUES (1, 'SEWN_TO_TOP', 'Sewn up to the top');
INSERT INTO `loop_sewing_type_tbl` (`loop_sewing_type_id`, `loop_sewing_type_abbr`, `loop_sewing_type_name`) VALUES (2, 'UNSEW_FIVE_FROM_TOP', 'Un-Sewing 5 CM from Top');
INSERT INTO `loop_sewing_type_tbl` (`loop_sewing_type_id`, `loop_sewing_type_abbr`, `loop_sewing_type_name`) VALUES (3, 'UNSEW_TEN_FROM_TOP', 'Un-Sewing 10 CM From Top');




--########################################   OTHER   ######################################## 

--Doc Pouch Type Table
INSERT INTO `other_doc_pouch_type_tbl` (`doc_pouch_type_id`, `doc_pouch_type_abbr`, `doc_pouch_type_name`) VALUES (1, 'A4_PORTRAIT', 'A 4 portrait');
INSERT INTO `other_doc_pouch_type_tbl` (`doc_pouch_type_id`, `doc_pouch_type_abbr`, `doc_pouch_type_name`) VALUES (2, 'ZIP_LOCK', 'Zip Lock');
INSERT INTO `other_doc_pouch_type_tbl` (`doc_pouch_type_id`, `doc_pouch_type_abbr`, `doc_pouch_type_name`) VALUES (3, 'A7_PORTRAIT', 'A 7 portrait');
INSERT INTO `other_doc_pouch_type_tbl` (`doc_pouch_type_id`, `doc_pouch_type_abbr`, `doc_pouch_type_name`) VALUES (4, 'A8_PORTRAIT', 'A 8 portrait');
INSERT INTO `other_doc_pouch_type_tbl` (`doc_pouch_type_id`, `doc_pouch_type_abbr`, `doc_pouch_type_name`) VALUES (5, 'A10_PORTRAIT', 'A 10 portrait');

--Doc Pouch Detail Type Table
INSERT INTO `other_doc_pouch_detail_type_tbl` (`doc_pouch_detail_type_id`, `doc_pouch_detail_type_abbr`, `doc_pouch_detail_type_name`) VALUES (1, 'TOP_SEAM', 'Top seam');
INSERT INTO `other_doc_pouch_detail_type_tbl` (`doc_pouch_detail_type_id`, `doc_pouch_detail_type_abbr`, `doc_pouch_detail_type_name`) VALUES (2, 'BOTTOM_SEAM', 'Bottom seam');
INSERT INTO `other_doc_pouch_detail_type_tbl` (`doc_pouch_detail_type_id`, `doc_pouch_detail_type_abbr`, `doc_pouch_detail_type_name`) VALUES (3, 'CENTER_SEAM', 'Center seam');

--Other Sides Table
INSERT INTO `other_sides_tbl` (`sides_id`, `sides_abbr`, `sides_name`) VALUES (1, 'ONE_SIDE', '1 side');
INSERT INTO `other_sides_tbl` (`sides_id`, `sides_abbr`, `sides_name`) VALUES (2, 'TWO_SIDE', '2 sides');
INSERT INTO `other_sides_tbl` (`sides_id`, `sides_abbr`, `sides_name`) VALUES (3, 'THREE_SIDE', '3 sides');
INSERT INTO `other_sides_tbl` (`sides_id`, `sides_abbr`, `sides_name`) VALUES (4, 'FOUR_SIDE', '4 sides');

--Other Printing Color Table
INSERT INTO `other_printing_color_tbl` (`printing_color_id`, `printing_color_name`, `printing_color_abbr`) VALUES (1, '1 Color', 'ONE_COLOR');
INSERT INTO `other_printing_color_tbl` (`printing_color_id`, `printing_color_name`, `printing_color_abbr`) VALUES (2, '2 Colors', 'TWO_COLOR');
INSERT INTO `other_printing_color_tbl` (`printing_color_id`, `printing_color_name`, `printing_color_abbr`) VALUES (3, '3 Colors', 'THREE_COLOR');
INSERT INTO `other_printing_color_tbl` (`printing_color_id`, `printing_color_name`, `printing_color_abbr`) VALUES (4, '4 Colors', 'FOUR_COLOR');

--Other Packaging Type Table
INSERT INTO `other_packaging_type_tbl` (`packaging_type_id`, `packaging_type_abbr`, `packaging_type_name`) VALUES (1, 'PALLET', 'Pallet');
INSERT INTO `other_packaging_type_tbl` (`packaging_type_id`, `packaging_type_abbr`, `packaging_type_name`) VALUES (2, 'BALE', 'Bale');

--Other Liner Type Table
INSERT INTO `other_liner_type_tbl` (`liner_type_id`, `liner_type_abbr`, `liner_type_name`) VALUES (1, 'PRE_SHAPE', 'Pre Shape');
INSERT INTO `other_liner_type_tbl` (`liner_type_id`, `liner_type_abbr`, `liner_type_name`) VALUES (2, 'BOTTLE_SHAPE', 'Bottle Shape');
INSERT INTO `other_liner_type_tbl` (`liner_type_id`, `liner_type_abbr`, `liner_type_name`) VALUES (3, 'FORM_FIT', 'Form Fit');
INSERT INTO `other_liner_type_tbl` (`liner_type_id`, `liner_type_abbr`, `liner_type_name`) VALUES (4, 'LOOSELY_FIT', 'Loosely Fit');

--########################################   Shipment   ######################################## 
INSERT INTO `shipment_type_tbl` (`shipment_type_id`, `shipment_type_abbr`, `shipment_type_name`) VALUES (1, 'FOB', 'Free on Board - FOB');

--########################################   PO Status   ########################################
INSERT INTO `po_tracking_status_master_tbl` (`po_status_id`, `po_status_abbr`, `po_status_name`, `po_status_order`, `po_status_is_main`, `mark_po_close`) VALUES (1, 'PO_RECEIVED', 'PO Received', 1, 1, 0);
INSERT INTO `po_tracking_status_master_tbl` (`po_status_id`, `po_status_abbr`, `po_status_name`, `po_status_order`, `po_status_is_main`, `mark_po_close`) VALUES (2, 'PO_UNDER_PROCESS', 'PO Under Process', 2, 1, 0);
INSERT INTO `po_tracking_status_master_tbl` (`po_status_id`, `po_status_abbr`, `po_status_name`, `po_status_order`, `po_status_is_main`, `mark_po_close`) VALUES (3, 'PO_UNDER_CONFIRMATION', 'PO Under Confirmation', 3, 0, 0);
INSERT INTO `po_tracking_status_master_tbl` (`po_status_id`, `po_status_abbr`, `po_status_name`, `po_status_order`, `po_status_is_main`, `mark_po_close`) VALUES (4, 'PO_CONFIRMED', 'PO Confirmed', 4, 0, 0);
INSERT INTO `po_tracking_status_master_tbl` (`po_status_id`, `po_status_abbr`, `po_status_name`, `po_status_order`, `po_status_is_main`, `mark_po_close`) VALUES (5, 'UNDER_PRODUCTION', 'Under Production', 5, 0, 0);
INSERT INTO `po_tracking_status_master_tbl` (`po_status_id`, `po_status_abbr`, `po_status_name`, `po_status_order`, `po_status_is_main`, `mark_po_close`) VALUES (6, 'PRODUCTION_COMPLETE', 'Production Complete', 6, 1, 0);
INSERT INTO `po_tracking_status_master_tbl` (`po_status_id`, `po_status_abbr`, `po_status_name`, `po_status_order`, `po_status_is_main`, `mark_po_close`) VALUES (7, 'DISPATCHED_FROM_FACTORY', 'Discpatched from Factory', 7, 1, 0);
INSERT INTO `po_tracking_status_master_tbl` (`po_status_id`, `po_status_abbr`, `po_status_name`, `po_status_order`, `po_status_is_main`, `mark_po_close`) VALUES (8, 'UNDER_ICD_EXAMINATION', 'Under Examination (At Nagpur ICD)', 8, 0, 0);
INSERT INTO `po_tracking_status_master_tbl` (`po_status_id`, `po_status_abbr`, `po_status_name`, `po_status_order`, `po_status_is_main`, `mark_po_close`) VALUES (9, 'RAILED_OUT', 'Railed Out', 9, 0, 0);
INSERT INTO `po_tracking_status_master_tbl` (`po_status_id`, `po_status_abbr`, `po_status_name`, `po_status_order`, `po_status_is_main`, `mark_po_close`) VALUES (10, 'CONTAINER_READY', 'Container Ready', 10, 0, 0);
INSERT INTO `po_tracking_status_master_tbl` (`po_status_id`, `po_status_abbr`, `po_status_name`, `po_status_order`, `po_status_is_main`, `mark_po_close`) VALUES (11, 'CONTAINER_DISPATCHED', 'Container Dispatched', 11, 1, 0);
INSERT INTO `po_tracking_status_master_tbl` (`po_status_id`, `po_status_abbr`, `po_status_name`, `po_status_order`, `po_status_is_main`, `mark_po_close`) VALUES (12, 'CONTAINER_DELIVERED', 'Container Delivered', 12, 1, 1);


