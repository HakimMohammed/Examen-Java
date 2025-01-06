classDiagram
direction BT
class Client {
   varchar(255) name
   varchar(255) email
   bigint id
}
class Commande {
   bigint client_id
   decimal(10,2) total_price
   bigint id
}
class Ingredient {
   varchar(255) name
   decimal(10,2) price
   bigint id
}
class PlatPrincipal {
   varchar(255) name
   decimal(10,2) base_price
   bigint id
}
class Repas {
   bigint main_dish_id
   decimal(10,2) total_price
   bigint id
}
class RepasCommande {
   bigint order_id
   bigint meal_id
}
class RepasIngredient {
   decimal(10,2) quantity
   bigint meal_id
   bigint ingredient_id
}
class RepasSupplement {
   bigint meal_id
   bigint supplement_id
}
class Supplement {
   varchar(255) name
   decimal(10,2) price
   bigint id
}

Commande  -->  Client : client_id:id
Repas  -->  PlatPrincipal : main_dish_id:id
RepasCommande  -->  Commande : order_id:id
RepasCommande  -->  Repas : meal_id:id
RepasIngredient  -->  Ingredient : ingredient_id:id
RepasIngredient  -->  Repas : meal_id:id
RepasSupplement  -->  Repas : meal_id:id
RepasSupplement  -->  Supplement : supplement_id:id
