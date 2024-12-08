select pl1_0.category_id,
       pl1_0.id,
       pl1_0.description,
       pl1_0.image,
       p1_0.id,
       p1_0.currency,
       p1_0.price,
       pl1_0.title
       from
           product pl1_0
         left join
           price p1_0
        on p1_0.id = pl1_0.price_id
where pl1_0.category_id = ?