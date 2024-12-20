-- https://documentation.red-gate.com/fd/execute-in-transaction-224919715.html
CREATE UNIQUE INDEX CONCURRENTLY
IF NOT EXISTS assembled_model_idx
ON cars
USING btree
(
    model text_ops, -- default operator class for text
    wheels int4_ops -- default operator class for integer
)
WHERE assembled_date IS NOT NULL;
