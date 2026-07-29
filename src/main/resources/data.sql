INSERT INTO misinformation_modules (title, description, category, content_url, created_at)
VALUES ('Identifying Deepfakes', 'Learn how to spot AI-generated media and deepfake videos across social platforms.', 'DIGITAL_LITERACY', 'https://example.com/modules/deepfakes', CURRENT_TIMESTAMP)
    ON CONFLICT DO NOTHING;