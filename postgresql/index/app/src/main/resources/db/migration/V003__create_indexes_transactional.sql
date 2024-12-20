CREATE INDEX submodel_idx
ON cars(submodel);

CREATE INDEX assembled_details_idx
ON cars
USING gin
(
    details jsonb_path_ops
)
WHERE assembled_date IS NOT NULL;
